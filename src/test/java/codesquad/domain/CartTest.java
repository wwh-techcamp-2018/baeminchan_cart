package codesquad.domain;

import codesquad.common.CartValue;
import codesquad.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
    private Product productOne;
    private Product productTwo;
    private Integer productOneNum;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        productOne = Product.builder()
                .id(1L)
                .title("곱창")
                .price(10_000L)
                .build();
        productTwo = Product.builder()
                .id(2L)
                .title("간장게장")
                .price(10_000L)
                .build();
        productOneNum = 3;
        cart = cartWithProduct(productOne, productOneNum);
    }

    @Test
    public void update_product_num() {
        cart.updateProductNum(productTwo.getId(), 4);

        assertThat(cart.getProducts().keySet()).contains(productOne.getId(), productTwo.getId());
        assertThat(cart.getProducts().keySet().size()).isEqualTo(2);
    }

    @Test
    public void get_sum_product_num() {
        Integer productTwoNum = 4;
        cart.updateProductNum(productTwo.getId(), productTwoNum);

        assertThat(cart.getSumProductNum()).isEqualTo(productOneNum + productTwoNum);
    }

    @Test
    public void set_user_if_not() {
        User user = User.builder().build();
        Cart cart = Cart.builder().build();
        cart.setUserIfNot(user);

        assertThat(cart.getUser()).isEqualTo(user);
    }

    @Test
    public void not_free_delivery_charge() {
        assertThat(Cart.builder().build()
                .getDeliveryCharge(CartValue.DELIVERY_CHARGE_REFERENCE - 1))
                .isEqualTo(2_500L);
    }

    @Test
    public void free_delivery_charge() {
        assertThat(Cart.builder().build()
                .getDeliveryCharge(CartValue.DELIVERY_CHARGE_REFERENCE))
                .isEqualTo(0);
    }

    @Test
    public void get_product_number() {
        assertThat(cart.productNum(productOne.getId())).isEqualTo(productOneNum);
    }

    @Test
    public void product_number_not_exist_product() {
        assertThat(Cart.builder().build().productNum(productOne.getId())).isEqualTo(0);
    }

    @Test
    public void products_id_list() {
        cart.updateProductNum(productTwo.getId(), 4);

        assertThat(cart.productsIdList()).contains(productOne.getId(), productTwo.getId());
    }

    @Test
    public void products_id_list_no_product() {
        assertThat(Cart.builder().build().productsIdList()).isEqualTo(Arrays.asList());
    }

    @Test
    public void check_empty_cart() {
        assertThat(Cart.builder().build().isEmpty()).isTrue();
        assertThat(cartWithProduct(productOne, 2).isEmpty()).isFalse();
    }

    @Test
    public void delete_product() {
        cart.deleteProduct(productOne.getId());

        assertThat(cart.getProducts()).isEmpty();
        assertThat(cart.getIsDeleted().keySet()).contains(productOne.getId());
        assertThat(cart.getIsDeleted().get(productOne.getId())).isEqualTo(productOneNum);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_wrong_product() {
        Cart.builder().build().deleteProduct(productOne.getId());
    }

    @Test
    public void delete_multi_products() {
        cart.updateProductNum(productTwo.getId(), 3);
        List<Long> productIds = Arrays.asList(productOne.getId(), productTwo.getId());

        cart.deleteMultiProducts(productIds);

        assertThat(cart.getProducts()).isEmpty();
    }

    private Cart cartWithProduct(Product product, Integer productNum) {
        LinkedHashMap<Long, Integer> productIds = new LinkedHashMap<>();
        productIds.put(product.getId(), productNum);
        return new Cart(productIds);
    }
}

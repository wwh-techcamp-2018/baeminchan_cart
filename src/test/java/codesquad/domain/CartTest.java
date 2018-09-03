package codesquad.domain;

import codesquad.common.CartValue;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
    private Product.ProductBuilder productBuilder;
    private Product.ProductBuilder otherProductBuilder;
    private Map<Product, Integer> products;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        productBuilder = Product.builder()
                .id(1L)
                .title("곱창")
                .price(10_000L);
        otherProductBuilder = Product.builder()
                .id(2L)
                .title("간장게장")
                .price(10_000L);

        cart = Cart.builder().build();
    }

    @Test
    public void update_product_num() {
        Product product = productBuilder.build();
        Product otherProduct = otherProductBuilder.build();

        cart.updateProductNum(product.getId(), 2);
        cart.updateProductNum(otherProduct.getId(), 4);

        assertThat(cart.getProducts().keySet()).contains(product.getId(), otherProduct.getId());
        assertThat(cart.getProducts().keySet().size()).isEqualTo(2);
    }

    @Test
    public void get_sum_product_num() {
        Product product = productBuilder.build();
        Product otherProduct = otherProductBuilder.build();

        cart.updateProductNum(product.getId(), 2);
        cart.updateProductNum(otherProduct.getId(), 4);

        assertThat(cart.getSumProductNum()).isEqualTo(6);
    }

    @Test
    public void set_user_if_not() {
        User user = User.builder().build();
        cart.setUserIfNot(user);

        assertThat(cart.getUser()).isEqualTo(user);
    }


    @Test
    public void notFreeDeliveryCharge() {
        assertThat(cart.getDeliveryCharge(CartValue.DELIVERY_CHARGE_REFERENCE - 1)).isEqualTo(2_500L);
    }

    @Test
    public void freeDeliveryCharge() {
        assertThat(cart.getDeliveryCharge(CartValue.DELIVERY_CHARGE_REFERENCE)).isEqualTo(0);
    }

    @Test
    public void productNum() {
        Product product = productBuilder.build();
        cart.updateProductNum(product.getId(), 2);

        assertThat(cart.productNum(product.getId())).isEqualTo(2);
    }

    @Test
    public void productNum_not_exist_product() {
        Product product = productBuilder.build();

        assertThat(cart.productNum(product.getId())).isEqualTo(0);
    }

    @Test
    public void productsIdList() {
        Product product = productBuilder.build();
        Product otherProduct = otherProductBuilder.build();

        cart.updateProductNum(product.getId(), 2);
        cart.updateProductNum(otherProduct.getId(), 4);

        assertThat(cart.productsIdList()).contains(product.getId(), otherProduct.getId());
    }

    @Test
    public void productsIdList_no_product() {
        assertThat(cart.productsIdList()).isEqualTo(Arrays.asList());
    }

    @Test
    public void emptyProductCart() {
        assertThat(cart.isEmpty()).isTrue();
        assertThat(cartWithProduct(productBuilder.build(), 2).isEmpty()).isFalse();
    }

    private Cart cartWithProduct(Product product, Integer productNum) {
        LinkedHashMap<Long, Integer> productIds = new LinkedHashMap<>();
        productIds.put(product.getId(), productNum);
        return new Cart(productIds);
    }
}

package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
    private Product.ProductBuilder productBuilder;
    private Product.ProductBuilder otherProductBuilder;
    private HashMap<Product, Integer> products;
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
    public void compute_10_same_product_discounted_under_twenty() {
        // Given
        Product product = productBuilder.build();
        Integer productNum = CartStaticValue.FREE_DEIVERY_PRODUCT_NUM;
        Cart cart = cartWithProduct(product, productNum);

        // When
        cart.totalProductsPrice(Arrays.asList(product));

        double discountedPrice = product.getPrice() *
                (1 - product.getDiscountRatio() - CartStaticValue.ADDITIONAL_DISCOUNT_RATIO) *
                productNum;

        // Then
        assertThat(cart.getProductsTotalPrice()).isEqualTo((long) discountedPrice);
    }

    @Test
    public void compute_9_same_product_discounted_under_twenty() {
        // Given
        Product product = productBuilder
                .discountRatio(CartStaticValue.DISCOUNT_RATIO_LIMIT - 0.1)
                .build();

        Integer productNum = CartStaticValue.FREE_DEIVERY_PRODUCT_NUM - 1;
        Cart cart = cartWithProduct(product, productNum);

        // When
        cart.totalProductsPrice(Arrays.asList(product));

        double discountedPrice = product.getPrice() *
                (1 - product.getDiscountRatio()) *
                productNum;

        // Then
        assertThat(cart.getProductsTotalPrice()).isEqualTo((long) discountedPrice);
    }

    @Test
    public void compute_10_same_product_discounted_over_twenty() {
        // Given
        Product product = otherProductBuilder
                .discountRatio(CartStaticValue.DISCOUNT_RATIO_LIMIT)
                .build();

        Integer productNum = CartStaticValue.FREE_DEIVERY_PRODUCT_NUM;
        Cart cart = cartWithProduct(product, productNum);

        // When
        cart.totalProductsPrice(Arrays.asList(product));

        double discountedPrice = product.getPrice() *
                (1 - product.getDiscountRatio()) *
                productNum;

        // Then
        assertThat(cart.getProductsTotalPrice()).isEqualTo((long) discountedPrice);
    }

    @Test
    public void notFreeDeliveryCharge() {
        Cart cart = Cart.builder().productsTotalPrice(39_999L).build();
        cart.updateDeliveryCharge();

        assertThat(cart.getDeliveryCharge()).isEqualTo(2_500L);
    }

    @Test
    public void freeDeliveryCharge() {
        Cart cart = Cart.builder().productsTotalPrice(40_000L).build();
        cart.updateDeliveryCharge();

        assertThat(cart.getDeliveryCharge()).isEqualTo(0);
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
        HashMap<Long, Integer> productIds = new HashMap<>();
        productIds.put(product.getId(), productNum);
        return new Cart(productIds);
    }
}

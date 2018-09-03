package codesquad.common;

import codesquad.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartValueTest {
    private Product.ProductBuilder productBuilder;

    @Before
    public void setUp() throws Exception {
        productBuilder = Product.builder()
                .price(10_000L)
                .discountRatio(CartValue.DISCOUNT_RATIO_LIMIT);
    }

    @Test
    public void ten_same_products_discounted_under_twenty() {
        // Given
        Product product = productBuilder
                .discountRatio(CartValue.DISCOUNT_RATIO_LIMIT - 0.01)
                .build();

        // When & Then
        assertThat(CartValue.getDiscountedPrice(product, CartValue.DISCOUNT_PRODUCT_NUM_GTE))
                .isEqualTo(computeDiscountedPrice(product, CartValue.ADDITIONAL_DISCOUNT_RATIO));
    }

    @Test
    public void nine_same_products_discounted_under_twenty() {
        // Given
        Product product = productBuilder
                .discountRatio(CartValue.DISCOUNT_RATIO_LIMIT - 0.01)
                .build();

        // When & Then
        assertThat(CartValue.getDiscountedPrice(product, CartValue.DISCOUNT_PRODUCT_NUM_GTE - 1))
                .isEqualTo(computeDiscountedPrice(product, 0));
    }

    @Test
    public void ten_same_products_discounted_gte_twenty() {
        // Given
        Product product = productBuilder
                .build();

        // When & Then
        assertThat(CartValue.getDiscountedPrice(product, CartValue.DISCOUNT_PRODUCT_NUM_GTE))
                .isEqualTo(computeDiscountedPrice(product, 0));
    }

    private long computeDiscountedPrice(Product product, double additionalDiscountRatio) {
        return (long) (product.getPrice() * (1 - product.getDiscountRatio() - additionalDiscountRatio));
    }
}

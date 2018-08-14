package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductTest {

    private Product productA;
    private Product productB;

    private static final Integer PRODUCT_COUNT = 10;

    @Before
    public void setUp() throws Exception {
        productA = Product.builder()
                .price(10000)
                .discountRate(10D)
                .build();

        productB = Product.builder()
                .price(10000)
                .discountRate(20D)
                .build();
    }

    @Test
    public void getSalesPrice() {
        assertThat(productA.getSalesPrice()).isEqualTo(9000);
        assertThat(productB.getSalesPrice()).isEqualTo(8000);
    }

    @Test
    public void getSalesPriceExtraDiscouunt() {
        assertThat(productA.getSalesPrice(PRODUCT_COUNT)).isEqualTo(8500 * PRODUCT_COUNT);
        assertThat(productB.getSalesPrice(PRODUCT_COUNT)).isEqualTo(8000 * PRODUCT_COUNT);
    }

}

package codesquad.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void 같은반찬_10개이상_주문() {
        Product product = new Product("총각김치", 10000L);
        assertThat(product.calculateSalePrice(10, 0.05)).isEqualTo(90000);
    }

    @Test
    public void 할인율_20프로_이상_반찬_10개이상_주문() {
        Product product = new Product("총각김치", 10000L);
        assertThat(product.calculateSalePrice(10, 0.2)).isEqualTo(80000);
    }

    @Test
    public void 구매금액_4만원_미만_배송비_포함() {
        Product product = new Product("총각김치", 1000L);
        assertThat(product.calculateSalePrice(1, 0.1)).isEqualTo(3400);
    }


    @Test
    public void 구매금액_4만원_이상_배송비_미포함() {
        Product product = new Product("총각김치", 100000L);
        assertThat(product.calculateSalePrice(1, 0.1)).isEqualTo(90000);
    }
}

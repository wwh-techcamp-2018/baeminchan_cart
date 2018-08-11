package codesquad.util;

import codesquad.domain.Product;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import static org.assertj.core.api.Assertions.assertThat;
public class SaleUtilsTest {

    Product productHasSaleRateTwenty;
    Product productHasSaleRateNineTeen;
    Product productHasSaleRateZero;
    private Long id;

    @Before
    public void setUp() throws Exception {
        productHasSaleRateTwenty = new Product(1L,"test","test","teest",6500L,20L);
        productHasSaleRateNineTeen = new Product(1L,"test","test","teest",6500L,19L);
        productHasSaleRateZero = new Product(1L,"test","test","teest",6500L,0L);

    }

    @Test
    public void checkDiscountTest_10개이상_할인율20미만() {
        assertThat(SaleUtils.checkDiscount(productHasSaleRateZero,10L)).isEqualTo(61750L);
    }

    @Test
    public void checkDiscountTest_10개미만_기본할인율0() {
        assertThat(SaleUtils.checkDiscount(productHasSaleRateZero,9L)).isEqualTo(58500L);
    }

    @Test
    public void checkDiscountTest_10개이상_할인율20이상() {
        assertThat(SaleUtils.checkDiscount(productHasSaleRateTwenty,10L)).isEqualTo(52000L);
    }

    @Test
    public void checkDiscountTest_10개이상_할인율20미만_추가할인() {
        assertThat(SaleUtils.checkDiscount(productHasSaleRateNineTeen,10L)).isEqualTo(50017L);
    }


    @Test
    public void checkDeliveryPriceTest_4만원이상_주문시() {
        assertThat(SaleUtils.checkDeliveryPrice(SaleUtils.DELIVERY_PRICE_LIMIT)).isEqualTo(SaleUtils.DELIVERY_PRICE_LIMIT);
    }

    @Test
    public void checkDeliveryPriceTest_4만원미만_주문시() {
        assertThat(SaleUtils.checkDeliveryPrice(30000L)).isEqualTo(30000 + SaleUtils.DELIVERY_PRICE);
    }

    @Test
    public void calculateSaleTest() {
        assertThat(SaleUtils.calculateSale(20L,6500L)).isEqualTo(5200);
    }
}
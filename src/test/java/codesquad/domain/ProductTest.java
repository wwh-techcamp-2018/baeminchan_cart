package codesquad.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

    SoftAssertions softly;

    @Before
    public void setUp(){
        softly = new SoftAssertions();
    }


    @Test
    public void productSaleCaseTest(){

        Long price = 10L;
        int amount = 10;
        int disCountableSaleRate = 14;
        int unDisCountableSaleRate = 20;
        Product disCountableProduct = new Product(price, disCountableSaleRate);
        Product unDisCountableProduct = new Product(price, unDisCountableSaleRate);

        softly.assertThat(disCountableProduct.calculatePrice(amount))
                .as("10개이상이라 할인 케이스")
                .isLessThan(price * amount * (100-disCountableSaleRate) / 100);

        softly.assertThat(unDisCountableProduct.calculatePrice(amount))
                .as("10개이상 인데 할인 할 수 없는 케이스")
                .isSameAs(price * amount * (100-unDisCountableSaleRate) / 100);

        softly.assertAll();

    }
}

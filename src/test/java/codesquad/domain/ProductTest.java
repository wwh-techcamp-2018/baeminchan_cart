package codesquad.domain;

import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;


public class ProductTest {
    Product testProduct;
    SoftAssertions softly;

    @Before
    public void setUp(){
        testProduct = new Product("test", "for test product", 4200);
        softly = new SoftAssertions();
    }

    @Test
    public void testCaculratePrice_할인률0(){
        assertThat(testProduct.caculatePrice(5L, 0L)).isEqualTo(21000L);
    }

    @Test
    public void testCaculratePrice_할인률0물량10(){
        assertThat(testProduct.caculatePrice(10L, 0L)).isEqualTo(39900L);
    }

    @Test
    public void testCaculratePrice_할인률20물량10(){
        assertThat(testProduct.caculatePrice(10L, 20L)).isEqualTo(33600L);
    }
}

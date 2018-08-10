package codesquad.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void discountPercentLt20() {
        assertThat(new Product(10_000L, 15.0).calculate(10)).isEqualTo(80750);
    }

    @Test
    public void discountPercentGte20() {
        assertThat(new Product(10_000L, 20.0).calculate(10)).isEqualTo(80000);
    }

    @Test
    public void discountPercentLt20ButCountLt10() {
        assertThat(new Product(10_000L, 15.0).calculate(9)).isEqualTo(76500);
    }
}

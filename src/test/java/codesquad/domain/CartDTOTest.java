package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartDTOTest {

    private List<Product> overPriceProductList;
    private List<Product> underPriceProductList;

    private Cart overPriceCart;
    private Cart underPriceCart;

    private CartDTO overPriceCartDTO;
    private CartDTO underPriceCartDTO;

    @Before
    public void setUp() throws Exception {
        overPriceProductList = Arrays.asList(
            new Product(1000L),
                new Product(20000L),
                new Product(20000L)
        );
        underPriceProductList = Arrays.asList(
                new Product(1000L),
                new Product(2000L),
                new Product(3000L)
        );
        overPriceCart = new Cart(overPriceProductList);
        underPriceCart = new Cart(underPriceProductList);

        overPriceCartDTO = new CartDTO(overPriceCart);
        underPriceCartDTO = new CartDTO(underPriceCart);
    }

    @Test
    public void priceMatchText() {
        assertThat(overPriceCartDTO.getPrice()).isEqualTo(41000L);
        assertThat(underPriceCartDTO.getPrice()).isEqualTo(8500L);
    }




}

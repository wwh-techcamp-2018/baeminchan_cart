package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {

    private List<Product> emptyProductList;

    private Cart overPriceCart;

    @Before
    public void setUp() throws Exception {
        emptyProductList = new ArrayList<Product>();
        overPriceCart = new Cart(emptyProductList);

    }

    @Test
    public void insertProductTest() {
        overPriceCart.addProduct(new Product(3000L));
        assertThat(overPriceCart.getProducts().size()).isEqualTo(1);
    }

    @Test
    public void multiInsertProductTest() {
        overPriceCart.addProducts(new Product(3000L), 5L);
        assertThat(overPriceCart.getProducts().size()).isEqualTo(5);
    }


}

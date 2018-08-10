package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
    private Product productOne;
    private List<Product> products;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        productOne = Product.builder()
                .title("집밥")
                .build();

        products = new ArrayList<>();
        products.add(productOne);

        cart = Cart.builder()
                .id(1L)
                .productList(products)
                .build();
    }

    @Test
    public void addProduct() {
        Product productTwo = Product.builder()
                .title("소고기국밥")
                .build();

        cart.addProduct(productTwo);
        assertThat(cart.getProductList().size()).isEqualTo(2L);
    }

    @Test
    public void getProductInCart() {
        assertThat(cart.productNumber()).isEqualTo(1L);
    }
}

package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
    private Product product;
    private Product addedProduct;
    private HashMap<Product, Integer> products;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        product = Product.builder().id(1L).title("곱창").build();
        addedProduct = Product.builder().id(2L).title("간장게장").build();

        cart = Cart.builder().build();
        cart.addProduct(product.getId(), 1);
    }

    @Test
    public void add_product() {
        cart.addProduct(addedProduct.getId(), 2);
        cart.addProduct(addedProduct.getId(), 4);
        assertThat(cart.getProducts().keySet()).contains(product.getId());
        assertThat(cart.getProducts().keySet()).contains(addedProduct.getId());
        assertThat(cart.getProducts().keySet().size()).isEqualTo(2);
    }

    @Test
    public void get_sum_product_num() {
        cart.addProduct(addedProduct.getId(), 2);
        cart.addProduct(addedProduct.getId(), 4);
        assertThat(cart.getSumProductNum()).isEqualTo(7);
    }

    @Test
    public void set_user_if_not() {
        User user = User.builder().build();
        cart.setUserIfNot(user);
        assertThat(cart.getUser()).isEqualTo(user);
    }
}

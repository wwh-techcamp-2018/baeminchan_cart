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
    public void addProduct() {
        cart.addProduct(addedProduct.getId(), 2);
        cart.addProduct(addedProduct.getId(), 4);
        assertThat(cart.getProducts().keySet()).contains(product.getId());
        assertThat(cart.getProducts().keySet()).contains(addedProduct.getId());
        assertThat(cart.getProducts().keySet().size()).isEqualTo(2);
    }

    @Test
    public void getSumProductNum() {
        cart.addProduct(addedProduct.getId(), 2);
        cart.addProduct(addedProduct.getId(), 4);
        assertThat(cart.getSumProductNum()).isEqualTo(7);
    }

    @Test
    public void matchUser() {
        User user = User.builder().build();
        Cart cart = Cart.builder().user(user).build();

        assertThat(cart.matchUser(user)).isTrue();
    }

    @Test
    public void notMatchUser() {
        User user = User.builder().build();
        User otherUser = User.builder().build();
        Cart cart = Cart.builder().user(user).build();

        assertThat(cart.matchUser(otherUser)).isFalse();
    }
}

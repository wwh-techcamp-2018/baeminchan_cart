package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {

    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void addProduct() {
        cart.addProduct(1L, 1);
        assertThat(cart.getProducts()).containsKey(1L);
    }

    @Test
    public void updateProduct() {
        cart.addProduct(1L, 1);
        cart.updateProduct(1L, 2);
        assertThat(cart.getProducts()).containsKey(1L).containsValue(2);
    }

    @Test
    public void deleteProduct() {
        cart.addProduct(1L, 1);
        cart.deleteProduct(1L, 1);
        assertThat(cart.getProducts()).doesNotContainKey(1L);
    }

    @Test
    public void deleteOverflowProductCount() {
        cart.addProduct(1L, 1);
        cart.deleteProduct(1L, 100);
        assertThat(cart.getProducts()).doesNotContainKey(1L);
    }

    @Test
    public void deleteNotExistProduct() {
        cart.deleteProduct(1L, 100);
        assertThat(cart.getProducts()).doesNotContainKey(1L);
    }
    
}
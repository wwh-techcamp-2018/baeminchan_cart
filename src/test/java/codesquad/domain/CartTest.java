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
    public void updateProduct() {
        cart.updateProduct(1L, 2);
        assertThat(cart.getProducts()).containsKey(1L).containsValue(2);
    }

    @Test
    public void updateProductZero() {
        cart.updateProduct(1L, 10);
        cart.updateProduct(1L, 0);
        assertThat(cart.getProducts()).doesNotContainKey(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateProductNegative() {
        cart.updateProduct(1L, 10);
        cart.updateProduct(1L, -1);
        assertThat(cart.getProducts()).doesNotContainKey(1L);
    }

    @Test
    public void deleteProduct() {
        cart.updateProduct(1L, 1);
        cart.deleteProduct(1L);
        assertThat(cart.getProducts()).doesNotContainKey(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNotExistProduct() {
        cart.deleteProduct(1L);
    }

}
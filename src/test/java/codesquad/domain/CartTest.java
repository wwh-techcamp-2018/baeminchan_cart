package codesquad.domain;

import codesquad.dto.CartProductDTO;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class CartTest {
    private Cart cart = new Cart();
    private CartProductDTO payload;

    @Test
    public void add() {
        payload = new CartProductDTO(1L, 1L);
        cart.add(payload);
        payload = new CartProductDTO(2L, 1L);
        cart.add(payload);
        payload = new CartProductDTO(3L, 1L);
        cart.add(payload);
        assertThat(cart.hasProduct(1L)).isTrue();
        assertThat(cart.hasProduct(2L)).isTrue();
        assertThat(cart.hasProduct(3L)).isTrue();
    }

    // TODO: 2018. 8. 10. After implement CartProductDTO validation check
    @Test
    public void add_null() {
        payload = new CartProductDTO(null, 1L);
        cart.add(payload);
        assertThat(cart.hasProduct(null)).isFalse();
    }

    @Test
    public void update_있는_상품() {
        payload = new CartProductDTO(1L, 1L);
        cart.add(payload);
        assertThat(cart.hasProduct(1L, 1L)).isTrue();
        payload = new CartProductDTO(1L, 5L);
        cart.update(payload);
        assertThat(cart.hasProduct(1L, 5L)).isTrue();
    }

    @Test
    public void update_없는_상품() {
        payload = new CartProductDTO(1L, 1L);
        cart.update(payload);
        assertThat(cart.hasProduct(1L)).isFalse();
    }

    @Test
    public void delete() {
        payload = new CartProductDTO(1L, 1L);
        cart.add(payload);
        assertThat(cart.hasProduct(1L)).isTrue();
        cart.delete(payload);
        assertThat(cart.hasProduct(1L)).isFalse();
    }

}
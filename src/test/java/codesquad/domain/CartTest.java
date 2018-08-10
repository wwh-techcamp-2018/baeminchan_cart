package codesquad.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {

    @Test
    public void addCartItem() {
        Cart cart = new Cart();
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(Product.builder().id(1L).build(), 1),
                new CartItem(Product.builder().id(2L).build(), 3)
        );

        for (CartItem cartItem : cartItems) {
            cart.addCartItem(cartItem);
        }

        assertThat(cart.getCartItems()).isEqualTo(cartItems);
    }


    @Test
    public void addExistsCartItem() {
        Cart cart = new Cart();
        Long productId = 1L;
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(Product.builder().id(productId).build(), 1),
                new CartItem(Product.builder().id(productId).build(), 3)
        );

        for (CartItem cartItem : cartItems) {
            cart.addCartItem(cartItem);
        }

        CartItem cartItem = cart.getCartItems().get(0);
        int addedCartItemCountSum = cartItems.stream().mapToInt(CartItem::getCount).sum();
        assertThat(cartItem.getCount()).isEqualTo(addedCartItemCountSum);
    }
}
package codesquad.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();


    public void addCartItem(CartItem cartItem) {
        Optional<CartItem> oldCartItem = cartItems.stream()
                .filter(item -> item.getProduct().equals(cartItem.getProduct()))
                .findFirst();

        if (oldCartItem.isPresent()) {
            mergeCartItem(oldCartItem.get(), cartItem);
            return;
        }

        cartItems.add(cartItem);
    }

    private void mergeCartItem(CartItem oldItem, CartItem newItem) {
        cartItems.remove(oldItem);
        cartItems.add(newItem.merge(oldItem));
    }
}

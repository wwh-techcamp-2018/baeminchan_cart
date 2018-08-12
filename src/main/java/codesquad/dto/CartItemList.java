package codesquad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashMap;

@Data
@AllArgsConstructor
public class CartItemList {
    private HashMap<Long, Long> cartItems;

    public CartItemList() {
        cartItems = new HashMap<>();
    }

    public void update(CartItem item) {
        if (item.getAmount() == 0) {
            delete(item);
        }
        cartItems.put(item.getId(), item.getAmount());
    }

    public void delete(CartItem item) {
        if (!cartItems.containsKey(item.getId()))
            throw new RuntimeException();
        cartItems.remove(item.getId());
    }

    public int getCartItemCount() {
        return cartItems.keySet().size();
    }
}

package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addCartItem(CartItem newCartItem){
        if(isPresentCartItem(newCartItem)) {
            itemCountUp(newCartItem);
            return;
        }
        cartItems.add(newCartItem);
    }

    private void itemCountUp(CartItem newCartItem) {
        cartItems.forEach(item -> {
            if (item.equals(getPresentCartItem(newCartItem))) {
                item.quantityCountUp();
            }
        });
    }

    private boolean isPresentCartItem(CartItem newCartItem) {
        Optional<CartItem> presentCartItem = getPresentCartItem(newCartItem);

        if(!presentCartItem.isPresent()) { return false; }
        return true;
    }

    private Optional<CartItem> getPresentCartItem(CartItem newCartItem) {
        return cartItems.stream().filter(item->
                            item.getProduct().equals(newCartItem.getProduct())).findFirst();
    }


}

package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private List<CartItem> itemList = new ArrayList<>();

    public CartDTO(Cart cart) {
        this(cart.getItemList());
    }

}

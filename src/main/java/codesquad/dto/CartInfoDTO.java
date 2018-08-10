package codesquad.dto;

import codesquad.domain.CartItem;
import codesquad.domain.Product;

import java.util.List;

public class CartInfoDTO {
    private String title;
    private int itemNumber;

    public CartInfoDTO(String title, int itemNumber) {
        this.title = title;
        this.itemNumber = itemNumber;
    }

    public static CartInfoDTO of(Product product, List<CartItem> cartItems){
        return new CartInfoDTO(product.getTitle(), cartItems.size());
    }

}

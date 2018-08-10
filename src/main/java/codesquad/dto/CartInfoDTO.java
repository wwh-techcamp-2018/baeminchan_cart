package codesquad.dto;

import codesquad.domain.CartItem;
import codesquad.domain.Product;

import java.util.List;

public class CartInfoDTO {
    private String title;
    private int itemNumber;

    public CartInfoDTO(){

    }

    public CartInfoDTO(int itemNumber){
        this("",itemNumber);
    }

    public CartInfoDTO(String title, int itemNumber) {
        this.title = title;
        this.itemNumber = itemNumber;
    }

    public static CartInfoDTO of(Product product, List<CartItem> cartItems){
        return new CartInfoDTO(product.getTitle(), cartItems.size());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }
}

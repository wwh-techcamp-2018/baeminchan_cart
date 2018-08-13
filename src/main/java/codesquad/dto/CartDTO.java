package codesquad.dto;

import java.io.Serializable;

public class CartDTO implements Serializable {
    private int productId;
    private int amount;

    public CartDTO(){

    }

    public CartDTO(int productId, int amount){
        this.productId = productId;
        this.amount = amount;
    }

    public long getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

package codesquad.dto;

import codesquad.domain.CartItem;

import java.io.Serializable;

public class CartItemDto implements Serializable {
    private Long productId;
    private int amount;

    public CartItemDto() {
    }

    public CartItemDto(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public CartItem toEntity() {
        return new CartItem();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

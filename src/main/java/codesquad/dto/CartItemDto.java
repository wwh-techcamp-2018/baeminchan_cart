package codesquad.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class CartItemDto {

    @NotNull
    private Long productId;

    @NotNull
    @DecimalMin(value = "0")
    private Integer quantity;

    public CartItemDto() {
    }

    public CartItemDto(@NotNull Long productId, @NotNull @DecimalMin(value = "0") Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package codesquad.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class CartItemDto {

    @NotNull
    private Long productId;

    @NotNull
    @DecimalMin(value = 0)
    private Integer count;

    public CartItemDto() {
    }

    public CartItemDto(@NotNull Long productId, @NotNull @DecimalMin(value = 0) Integer count) {
        this.productId = productId;
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

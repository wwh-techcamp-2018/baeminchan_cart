package codesquad.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemDTO {
    private Long productId;
    private Integer count;

    @Builder
    public CartItemDTO(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}

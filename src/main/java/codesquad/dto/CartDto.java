package codesquad.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {
    private String productId;
    private Integer productNum;

    @Builder
    public CartDto(String productId, Integer productNum) {
        this.productId = productId;
        this.productNum = productNum;
    }
}

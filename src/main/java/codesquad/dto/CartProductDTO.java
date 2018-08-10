package codesquad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    // TODO: 2018. 8. 10. Need validation check for fields
    private Long productId;

    private Long quantity;

    @Override
    public String toString() {
        return "productId: " + productId + ", quantity: " + quantity;
    }
}

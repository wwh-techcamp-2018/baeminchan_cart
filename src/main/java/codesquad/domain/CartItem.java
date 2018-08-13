package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class CartItem {
    private Product product;
    private Integer quantity;

    public void quantityCountUp() {
        this.quantity += 1;
    }
}

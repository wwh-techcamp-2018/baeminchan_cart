package codesquad.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CartProduct {
    private Product product;
    private int count;

    public CartProduct(Product product, int count) {
        this.product = product;
        this.count = count;
    }
}

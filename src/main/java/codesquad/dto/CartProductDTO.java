package codesquad.dto;

import codesquad.domain.CartProduct;
import codesquad.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CartProductDTO {
    private Long productId;
    private int count;

    public CartProductDTO(Long productId, int count) {
        this.productId = productId;
        this.count = count;
    }

    public CartProduct toCartProduct(Product product) {
        return new CartProduct(product, this.count);
    }
}

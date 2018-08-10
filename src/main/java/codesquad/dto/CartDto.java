package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class CartDto {
    @NotBlank
    private Long productId;

    private Product product;

    @NotBlank
    @DecimalMin("1")
    private Long quantity;

    public CartDto(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart toCart(Product product, User buyer) {
        return new Cart(buyer, product, quantity);
    }
}

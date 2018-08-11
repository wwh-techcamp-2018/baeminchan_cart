package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.CartProduct;
import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.support.PriceCalcultor;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Data

//todo builder 삭제
@Builder @AllArgsConstructor
public class CartProductDTO {
    private Product product;
    private Cart cart;
    @NotNull
    @Min(1)
    private int count = 1;
    @NotNull
    private Long productId;
    private Long totalPrice;
    public CartProduct toEntity() {
        return new CartProduct(this);
    }

    public void fill(@NotNull Cart cart, @NotNull Product product, PriceCalcultor priceCalcultor){
        this.cart = cart;
        this.product = product;
        this.totalPrice = product.calculatePrice(priceCalcultor, this.count);
    }
}

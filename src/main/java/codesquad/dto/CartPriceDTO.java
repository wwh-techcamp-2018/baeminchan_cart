package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartPriceDTO {
    private Long totalProductPrice;
    private Long deliveryCharge;
    private Long totalPrice;

    @Builder
    public CartPriceDTO(Long totalProductPrice, Long deliveryCharge, Long totalPrice) {
        this.totalProductPrice = totalProductPrice;
        this.deliveryCharge = deliveryCharge;
        this.totalPrice = totalPrice;
    }

    public static CartPriceDTO from(Cart cart, List<Product> products) {
        Long productsPrice = cart.computeTotalProductsPrice(products);
        Long deliveryCharge = cart.getDeliveryCharge(productsPrice);

        return CartPriceDTO.builder()
                .totalProductPrice(productsPrice)
                .deliveryCharge(deliveryCharge)
                .totalPrice(productsPrice + deliveryCharge)
                .build();
    }
}

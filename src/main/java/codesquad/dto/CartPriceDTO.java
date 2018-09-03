package codesquad.dto;

import codesquad.domain.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public static CartPriceDTO from(Cart cart) {
        Long productsPrice = 0L;
        Long deliveryCharge = 0L;

        if (!cart.isEmpty()) {
            productsPrice = cart.getProductsTotalPrice();
            deliveryCharge = cart.getDeliveryCharge();
        }

        return CartPriceDTO.builder()
                .totalProductPrice(productsPrice)
                .deliveryCharge(deliveryCharge)
                .totalPrice(productsPrice + deliveryCharge)
                .build();
    }
}

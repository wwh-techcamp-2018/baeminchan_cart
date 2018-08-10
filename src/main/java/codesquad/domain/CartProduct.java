package codesquad.domain;

import codesquad.dto.CartProductDTO;

public class CartProduct {
    private Product product;
    private int amount;
    private boolean isDelivered;

    public CartProduct() {
    }

    private CartProduct(Product product, int amount, boolean isDelivered) {
        this.product = product;
        this.amount = amount;
        this.isDelivered = isDelivered;
    }

    public static CartProduct ofDTO(CartProductDTO productDTO, Product product) {
        return new CartProduct(product, productDTO.getAmount(), productDTO.isDelivered());
    }

    public Long getPrice() {
        return product.getPriceByAmount(amount);
    }
}

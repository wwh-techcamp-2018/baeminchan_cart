package codesquad.domain;

import java.util.List;

public class CartDTO {

    private List<ProductDTO> productDTOList;
    private Long price;
    private Long displayPrice;

    private Cart cart;

    public CartDTO() {

    }

    public CartDTO(Cart cart) {
        this.cart = cart;
        this.productDTOList = cart.getProductDTOList();
        this.price = cart.calculatePrice();
        this.displayPrice = cart.calculateDisplayPrice();
    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public Long getPrice() {
        return price;
    }

    public Long getDisplayPrice() {
        return displayPrice;
    }
}

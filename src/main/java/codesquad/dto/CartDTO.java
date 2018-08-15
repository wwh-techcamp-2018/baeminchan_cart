package codesquad.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Optional;

@JsonPropertyOrder({ "cartProducts", "totalPrice", "shippingFee" })
public class CartDTO {

    private static final Integer SHIPPING_FEE = 2500;
    private static final Integer FREE_SHIPPING_THRESHOLD = 40_000;

    private List<CartProductDTO> cartProductList;

    private Integer totalSumPrice;

    private Integer shippingFee;

    public CartDTO() {

    }

    public CartDTO(List<CartProductDTO> cartProductList) {
        this.cartProductList = cartProductList;
        this.totalSumPrice = cartProductList.stream().mapToInt(CartProductDTO::getSumPrice).sum();
        this.shippingFee = totalSumPrice < FREE_SHIPPING_THRESHOLD ? SHIPPING_FEE : 0;
    }

    public List<CartProductDTO> getCartProducts() {
        return cartProductList;
    }

    public Integer getTotalPrice() {
        return totalSumPrice;
    }

    public Integer getShippingFee() {
        return shippingFee;

    }

}

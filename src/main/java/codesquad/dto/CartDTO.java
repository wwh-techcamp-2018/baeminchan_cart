package codesquad.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({ "cartProducts", "sumPrice", "shippingFee", "totalPrice" })
public class CartDTO {

    private static final Integer SHIPPING_FEE = 2500;
    private static final Integer FREE_SHIPPING_THRESHOLD = 40_000;

    private List<CartProductDTO> cartProductList;

    private Integer sumPrice;

    private Integer totalPrice;

    private Integer shippingFee;

    public CartDTO() {
        this(new ArrayList<>());
    }

    public CartDTO(List<CartProductDTO> cartProductList) {
        this.cartProductList = cartProductList;
        this.sumPrice = cartProductList.stream().mapToInt(CartProductDTO::getSumPrice).sum();
        this.shippingFee = sumPrice < FREE_SHIPPING_THRESHOLD ? SHIPPING_FEE : 0;
        this.totalPrice = sumPrice + (sumPrice > 0 ? shippingFee : 0);
    }

    public List<CartProductDTO> getCartProducts() {
        return cartProductList;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

}

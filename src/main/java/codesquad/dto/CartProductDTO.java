package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CartProductDTO {
    private static final Integer CART_NUMBER_REFERENCE = 10;
    private static final double DISCOUNT_RATIO_REFERENCE = 0.2;
    private static final double ADDED_DISCOUNT_RATIO = 0.05;

    private Long productId;
    private String imgUrl;
    private String title;
    private Long price;
    private Integer productNum;
    private Long totalPrice;
    private boolean regularDelivery;

    @Builder
    public CartProductDTO(Long productId, String imgUrl, String title, Long price, Integer productNum, Long totalPrice, boolean regularDelivery) {
        this.productId = productId;
        this.imgUrl = imgUrl;
        this.title = title;
        this.price = price;
        this.productNum = productNum;
        this.totalPrice = totalPrice;
        this.regularDelivery = regularDelivery;
    }

    public static CartProductDTO from(Cart cart, Product product) {
        Long productId = product.getId();
        Integer productNum = cart.productNum(productId);
        Long discountedPrice = (long) (product.getPrice() * (1 - computeDiscountRatio(product.getDiscountRatio(), productNum)));

        return CartProductDTO.builder()
                .productId(productId)
                .imgUrl(product.getImgUrl())
                .title(product.getTitle())
                .price(discountedPrice)
                .productNum(productNum)
                .totalPrice(discountedPrice * productNum)
                .build();
    }

    public static List<CartProductDTO> listFrom(Cart cart, List<Product> products) {
        return products.stream()
                .map((product) -> from(cart, product))
                .collect(Collectors.toList());
    }

    private static double computeDiscountRatio(double discountRatio, Integer productNum) {
        return (productNum >= CART_NUMBER_REFERENCE && discountRatio < DISCOUNT_RATIO_REFERENCE) ? discountRatio + ADDED_DISCOUNT_RATIO : discountRatio;
    }

}

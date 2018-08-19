package codesquad.dto;

import codesquad.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartProductDto {
    private static final Integer CART_NUMBER_REFERENCE = 10;
    private static final double DISCOUNT_RATIO_REFERENCE = 0.2;
    private static final double ADDED_DISCOUNT_RATIO = 0.05;

    private String imgUrl;
    private String title;
    private Long price;
    private Integer productNum;
    private Long totalPrice;
    private boolean regularDelivery;

    @Builder
    public CartProductDto(String imgUrl, String title, Long price, Integer productNum, Long totalPrice, boolean regularDelivery) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.price = price;
        this.productNum = productNum;
        this.totalPrice = totalPrice;
        this.regularDelivery = regularDelivery;
    }

    public static CartProductDto from(Product product, Integer productNum) {
        long discountedPrice = (long) (product.getPrice() * (1 - computeDiscountRatio(product.getDiscountRatio(), productNum)));

        return CartProductDto.builder()
                .imgUrl(product.getImgUrl())
                .title(product.getTitle())
                .price(discountedPrice)
                .productNum(productNum)
                .totalPrice(discountedPrice * productNum)
                .build();
    }

    private static double computeDiscountRatio(double discountRatio, Integer productNum) {
        return (productNum >= CART_NUMBER_REFERENCE && discountRatio < DISCOUNT_RATIO_REFERENCE) ? discountRatio + ADDED_DISCOUNT_RATIO : discountRatio;
    }

}

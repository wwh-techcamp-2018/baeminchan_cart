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
        return CartProductDto.builder()
                .imgUrl(product.getImgUrl())
                .title(product.getTitle())
                .price(product.getPrice())
                .productNum(productNum)
                .totalPrice(product.getPrice() * productNum)
                .build();
    }

}

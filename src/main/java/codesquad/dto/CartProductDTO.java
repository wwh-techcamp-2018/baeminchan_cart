package codesquad.dto;

import codesquad.domain.Product;

public class CartProductDTO {

    private static final Integer EXTRA_DISCOUNT_RATE = 5;
    private static final Integer EXTRA_DISCOUNT_LIMIT = 20;
    private static final Integer EXTRA_DISCOUNT_THRESHOLD = 10;

    private Product product;

    private Integer count;

    public CartProductDTO() {

    }

    public CartProductDTO(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getSumPrice() {
        Integer price = product.getPrice();
        Integer discountRate = product.getDiscountRate();

        if (EXTRA_DISCOUNT_THRESHOLD <= count && discountRate < EXTRA_DISCOUNT_LIMIT) {
            discountRate += EXTRA_DISCOUNT_RATE;
        }
        return (price - Double.valueOf((price * discountRate) / 100).intValue()) * count;
    }

    public Integer getCount() {
        return count;
    }

}

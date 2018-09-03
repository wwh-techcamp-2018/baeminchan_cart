package codesquad.common;

import codesquad.domain.Product;

public class CartValue {
    public static final Long DELIVERY_CHARGE_REFERENCE = 40_000L;
    public static final Long DELIVERY_CHARGE = 2_500L;
    public static final Integer DISCOUNT_PRODUCT_NUM_GTE = 10;
    public static final double DISCOUNT_RATIO_LIMIT = 0.2;
    public static final double ADDITIONAL_DISCOUNT_RATIO = 0.05;

    public static long getDiscountedPrice(Product product, Integer productNum) {
        double discountRatio = product.getDiscountRatio();
        double updatedRatio = (productNum >= DISCOUNT_PRODUCT_NUM_GTE && discountRatio < DISCOUNT_RATIO_LIMIT)
                ? (1 - discountRatio - ADDITIONAL_DISCOUNT_RATIO)
                : (1 - discountRatio);

        return (long) (product.getPrice() * updatedRatio);
    }
}

package codesquad.support;

public class PriceCalcultor {
    public static final long DELIVERY_FEE = 2500L;
    public static final long DELIVERY_FEE_FREE_THRESHOLD = 40000L;
    public static final long NO_DISCOUNT_THRESHOLD = 20L;
    public static final int DISCOUNT_AMT_THRESHOLD = 10;
    public static final long ADDITIONAL_DISCOUNT_RATE = 5L;

    public Long calculatePrice(Long price, Long discountRate, int count) {
        if(discountRate < NO_DISCOUNT_THRESHOLD && count >= DISCOUNT_AMT_THRESHOLD)
            discountRate += ADDITIONAL_DISCOUNT_RATE;
        return price *  (100L - discountRate) /100L * count;
    }

    public Long calculateTotalPrice(long originalTotalPrice){
        if(DELIVERY_FEE_FREE_THRESHOLD > originalTotalPrice)
            originalTotalPrice += DELIVERY_FEE;
        return originalTotalPrice;
    }
}

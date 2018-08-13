package codesquad.support;

public class PriceCalcultor {
    public static final long DELIVERY_FEE = 2500L;
    public static final long DELIVERY_FEE_FREE_THRESHOLD = 40000L;
    public static final long NO_DISCOUNT_THRESHOLD = 20L;
    public static final int DISCOUNT_AMT_THRESHOLD = 10;
    public static final long ADDITIONAL_DISCOUNT_RATE = 5L;

    private static PriceCalcultor priceCalcultor = new PriceCalcultor();
    //hint 아래는 Bean으로 등록하고 사용하려다가 실패한 흔적들
    public Long calculatePrice(Long price, Long discountRate, int count) {
        if(discountRate < NO_DISCOUNT_THRESHOLD && count >= DISCOUNT_AMT_THRESHOLD)
            discountRate += ADDITIONAL_DISCOUNT_RATE;
        return price *  (100L - discountRate) /100L * count;
    }

    public Long calculateDeliveryFee(long originalTotalPrice){
        if(DELIVERY_FEE_FREE_THRESHOLD <= originalTotalPrice || originalTotalPrice == 0)
            return 0L;
        return DELIVERY_FEE;
    }
    //hint 아래는 util성 클래스로 바꾼 흔적들
    public static PriceCalcultor getInstance(){
        return priceCalcultor;
    }
    public static Long calculatePrice_2(Long price, Long discountRate, int count) {
        if(discountRate < NO_DISCOUNT_THRESHOLD && count >= DISCOUNT_AMT_THRESHOLD)
            discountRate += ADDITIONAL_DISCOUNT_RATE;
        return price *  (100L - discountRate) /100L * count;
    }

    public static Long calculateDeliveryFee_2(long originalTotalPrice){
        if(DELIVERY_FEE_FREE_THRESHOLD <= originalTotalPrice || originalTotalPrice == 0)
            return 0L;
        return DELIVERY_FEE;
    }
}

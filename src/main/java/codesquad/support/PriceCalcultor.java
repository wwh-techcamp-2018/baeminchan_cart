package codesquad.support;

public class PriceCalcultor {
    public Long calculatePrice(Long price, Long discountRate, int count) {
        return price *  discountRate * count;
    }
}

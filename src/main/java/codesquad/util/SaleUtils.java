package codesquad.util;

import codesquad.domain.Product;

public class SaleUtils {

    public static final Long DELIVERY_PRICE_LIMIT = 40000L;
    public static final Long DELIVERY_PRICE = 2500L;
    public static final Long SALE_RATE = 5L;

    public static Long checkDiscount(Product product, Long ea) {
        Long salePrice = calculateSale(product.getSale(), product.getPrice() * ea);

        if (ea < 10) return salePrice;
        return (product.getSale() < 20) ? calculateSale(SALE_RATE, salePrice) : salePrice;
    }

    public static Long checkDeliveryPrice(Long price) {
        return price >= DELIVERY_PRICE_LIMIT ? price : price + DELIVERY_PRICE;
    }

    public static Long calculateSale(Long saleRate, Long price) {
        return (price * (100 - saleRate)) / 100;
    }
}

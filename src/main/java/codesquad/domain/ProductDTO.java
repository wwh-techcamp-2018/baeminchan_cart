package codesquad.domain;

public class ProductDTO {

    private Product product;
    private Long count;
    private Long priceSum;

    private final int SALE_THRESHOLD = 10;

    ProductDTO() {

    }

    ProductDTO(Product product, Long count) {
        this.product = product;
        this.count = count;
        if(count >= SALE_THRESHOLD) {
            this.priceSum = ((int) (product.getPrice() * 0.95)) * count;
        } else {
            this.priceSum = product.getPrice() * count;
        }
    }

    public Product getProduct() {
        return product;
    }

    public Long getCount() {
        return count;
    }

    public Long getPriceSum() {
        return priceSum;
    }
}

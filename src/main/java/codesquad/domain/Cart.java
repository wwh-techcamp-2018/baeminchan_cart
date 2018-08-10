package codesquad.domain;

public class Cart {
    private Product product;
    private int count;
    private Long totalPrice;
    private boolean isDeleted;

    public Cart() {
        this.isDeleted = false;
    }

    public Cart(Product product, int count) {
        this.product = product;
        this.count = count;
        this.totalPrice = product.getTotalPrice(count);
        this.isDeleted = false;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void updateCount(int count) {
        this.count = count;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void delete() {
        isDeleted = true;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "product=" + product +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", isDeleted=" + isDeleted +
                '}';
    }
}


/***
 * 1. Cart 단위 테스트 구현
 * 2. Cart API Controller 구현
 *
 */

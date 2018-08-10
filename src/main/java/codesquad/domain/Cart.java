package codesquad.domain;

public class Cart {
    private Product product;
    private Long quantity;
    private Long totalPrice;
    private Long saleRate;

    public void addToCart(Product product, Long quantity, Long saleRate) {
        this.product = product;
        this.quantity = quantity;
        this.saleRate = saleRate;
        this.totalPrice = product.caculatePrice(quantity, saleRate);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", saleRate=" + saleRate +
                '}';
    }

    public void changeQuantity(Long changeValue) {
        if(this.quantity + changeValue < 0) throw new RuntimeException();
        quantity = quantity + changeValue;
        totalPrice = product.caculatePrice(quantity, saleRate);
    }
}

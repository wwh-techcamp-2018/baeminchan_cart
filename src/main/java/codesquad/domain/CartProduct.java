package codesquad.domain;

import java.io.Serializable;
import java.util.Objects;

public class CartProduct implements Serializable {

    private Long productId;
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public CartProduct(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void add(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return Objects.equals(this.productId, (Long) o);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId);
    }
}

package codesquad.domain;

import java.util.Objects;

public class Merchandise {

    private Long pId;
    private int amount;
    private boolean isDelivery = true;
    private Product product;

    public Merchandise() {
    }

    public Merchandise(int amount, boolean isDelivery, Product product) {
        this.amount = amount;
        this.isDelivery = isDelivery;
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchandise that = (Merchandise) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }
}

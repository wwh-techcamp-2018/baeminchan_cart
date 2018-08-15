package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchandise {

    private Long pId;
    private int amount;
    private boolean isDelivery = true;
    private Product product;

    public Merchandise(int amount, boolean isDelivery, Product product) {
        this.amount = amount;
        this.isDelivery = isDelivery;
        this.product = product;
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
}

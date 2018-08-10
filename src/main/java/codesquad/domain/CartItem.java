package codesquad.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class CartItem {
    private Product product;
    private int count;

    public CartItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public CartItem merge(CartItem cartItem) {
        return new CartItem(this.product, this.count + cartItem.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return count == cartItem.count &&
                Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product, count);
    }
}

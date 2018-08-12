package codesquad.dto;

import codesquad.domain.CartProduct;
import codesquad.domain.Product;
import codesquad.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.util.Objects;

@Data
@AllArgsConstructor
public class CartProductDTO {
    private Product product;
    private BigInteger count;

    public CartProductDTO(Product product) {
        this.product = product;
    }

    public CartProduct toCartItem(User loginUser) {
        return new CartProduct(loginUser, this.product, this.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductDTO that = (CartProductDTO) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}

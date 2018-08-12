package codesquad.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Objects;

@Data
public class ProductDTO {
    private long productId;
    // 수량을 무한대로 받아주기 위해 BigInteger 사용
    private BigInteger count;

    public ProductDTO setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public ProductDTO setCount(BigInteger count) {
        this.count = count;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return productId == that.productId &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, count);
    }
}

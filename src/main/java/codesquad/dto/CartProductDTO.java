package codesquad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {
    private Long id;
    private String title;
    private Long count;
    private Long price;

    public CartProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public CartProductDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public CartProductDTO setCount(Long count) {
        this.count = count;
        return this;
    }

    public CartProductDTO setPrice(Long price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductDTO that = (CartProductDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, count);
    }
}


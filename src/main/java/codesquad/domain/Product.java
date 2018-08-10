package codesquad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 255)
    private String title;

    @Size(min = 1, max = 255)
    private String description;

    @Size(min = 1, max = 255)
    private String imgUrl;

    @DecimalMin(value = "0")
    private Long price;

    @Column
    @Size(min = 0, max = 100)
    private Long discountRatio = 0L;

    @Builder
    public Product(@Size(min = 1, max = 255) String title, @Size(min = 1, max = 255) String description,
                   @Size(min = 1, max = 255) String imgUrl, @DecimalMin(value = "0") Long price,
                   @Size(min = 0, max = 100) Long discountRatio) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.discountRatio = discountRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }
}

package codesquad.domain;

import codesquad.dto.ProductBundleInputDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductBundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @Column(nullable = false)
    @Min(1)
    private long count;


    public ProductBundle update(ProductBundleInputDto dto) {
        count += dto.getCount();
        return this;
    }

    public ProductBundle reset(ProductBundleInputDto dto) {
        count = dto.getCount();
        return this;
    }

    public boolean isSameProduct(ProductBundle bundle) {
        return product.isSameId(bundle.product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBundle bundle = (ProductBundle) o;
        return id == bundle.id;
    }
}

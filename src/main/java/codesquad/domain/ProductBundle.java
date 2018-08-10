package codesquad.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductBundle {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @NotNull
    @OneToOne
    private Product product;

    @NotNull
    @OneToOne
    private Cart cart;

    @Getter
    @NotNull
    @Column(nullable = false)
    @Min(1)
    private long count;

    public ProductBundle update(ProductBundle bundle) {
        count = bundle.count;
        return this;
    }
}

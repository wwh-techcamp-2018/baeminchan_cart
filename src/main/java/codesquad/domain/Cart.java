package codesquad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> productList;

    @DecimalMin(value = "0")
    private Long deliveryCharge;

    @Builder
    public Cart(Long id, User user, List<Product> productList, Long deliveryCharge) {
        this.id = id;
        this.user = user;
        this.productList = productList;
        this.deliveryCharge = deliveryCharge;
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public int productNumber() {
        return productList.size();
    }
}

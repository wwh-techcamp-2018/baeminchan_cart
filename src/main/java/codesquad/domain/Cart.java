package codesquad.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany
    private List<Product> productList;

    @DecimalMin(value = "0")
    private Long totalPrice;

    @DecimalMin(value = "0")
    private Long deliveryCharge;
}

package codesquad.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @NotNull
    @Column(nullable = false)
    @MapKey(name="id")
    @OneToMany(mappedBy = "cart")
    private Map<Long, ProductBundle> bundles;

    static Cart testCart() {
        Cart cart = new Cart();
        cart.bundles = new HashMap<>();
        return cart;
    }

    public ProductBundle saveProductBundle(ProductBundle productBundle) {
        Optional<ProductBundle> maybeBundle = Optional.ofNullable(bundles.get(productBundle.getId()));
        if (maybeBundle.isPresent())
            return maybeBundle.get().update(productBundle);

        return bundles.put(productBundle.getId(), productBundle);
    }

    public ProductBundle removeProductBundle(ProductBundle productBundle) {
        Optional<ProductBundle> maybeBundle = Optional.ofNullable(bundles.get(productBundle.getId()));
        if (!maybeBundle.isPresent()) {
            // TODO: raise exception for bad request
        }
        return bundles.remove(productBundle.getId());
    }
}

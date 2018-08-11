package codesquad.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    private List<CartItem> items = new ArrayList<>();

    @DecimalMin(value = "0")
    private Long totalItemPrice = 0L;

    @DecimalMin(value = "0")
    private Long shippingCharge = 0L;

    @DecimalMin(value = "0")
    private Long totalPrice = 0L;

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public int itemCount() { return items.size(); }

    public CartItem getCartItem(Product product, Integer quantity) {
        Optional<CartItem> maybeCartItem = items.stream()
                .filter(item -> (item.getProduct().getId() == product.getId()))
                .findFirst();

        if (!maybeCartItem.isPresent())
            return new CartItem(product, quantity, this);

        CartItem cartItem = maybeCartItem.get();
        cartItem.setCount(quantity);
        return cartItem;
    }

    public static Cart of() {
        return new Cart();
    }
}

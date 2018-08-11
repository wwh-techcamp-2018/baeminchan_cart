package codesquad.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;

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

    public List<CartItem> getItems() {
        return items;
    }

    public void add(CartItem item) {
        items.add(item);
    }

    public boolean contains(CartItem item) {
        return items.contains(item);
    }

    public int itemCount() { return items.size(); }

    public CartItem get(Long itemId) {
        return items.stream()
                .filter(item -> item.getId() == itemId)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    public static Cart of() {
        return new Cart();
    }
}

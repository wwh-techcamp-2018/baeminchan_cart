package codesquad.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public Cart() {
    }

    private Cart(User user, List<CartItem> cartItems) {
        this.user = user;
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public static Cart createOf(User user, CartItem cartItem) {
        return new Cart(user, Arrays.asList(cartItem));
    }

    public Cart add(CartItem cartItem) {
        this.cartItems.add(cartItem);
        return this;
    }
}

package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    public static final Cart CART1 = new Cart(Arrays.asList(CartItem.SALMON, CartItem.PIZZA));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<CartItem> itemList = new ArrayList<>();

    public Cart(List<CartItem> itemList) {
        this.itemList = itemList;
    }

    public List<CartItem> getItemList() {
        return itemList;
    }
}

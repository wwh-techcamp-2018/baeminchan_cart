package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    public static final CartItem SALMON = new CartItem(Product.SALMON, 1);
    public static final CartItem PIZZA = new CartItem(Product.PIZZA, 2);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    //TODO 수량은 무한대로 입력 가능
    @Size(min = 1)
    private int count;

    public CartItem(Product product, @Size(min = 1) int count) {
        this.product = product;
        this.count = count;
    }
}

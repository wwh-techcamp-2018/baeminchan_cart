package codesquad.domain;

import codesquad.dto.BasketDTO;
import codesquad.dto.BasketProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Entity
@NoArgsConstructor
public class CartHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_history_user"))
    private User user;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_history_product"))
    private Product product;

    private Long ea;

    @Column(columnDefinition = "bool default false")
    private boolean purchase;

    public CartHistory(User user, Product product,Long ea) {
        this.user = user;
        this.product = product;
        this.ea = ea;
    }
}

package codesquad.domain;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@ToString
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basket_id;

    @NotNull
    @Column
    private Long cartId;

    @NotNull
    @Column
    private Long productId;

    @NotNull
    @Column
    private int amount;

    @Column
    private Status status;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean deleted;

    public void updateAmount(int amount) {
        this.amount = amount;
    }
}

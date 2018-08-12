package codesquad.domain;

import codesquad.enums.CartItemState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
public class CartProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    private Product product;

    @NonNull
    @Column
    private BigInteger count;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    @Enumerated(EnumType.STRING)
    private CartItemState cartItemState;

    @NonNull
    @Column
    private LocalDateTime createTime;

    public void recordDeleteState() {
        cartItemState = CartItemState.DELETE;
    }
}

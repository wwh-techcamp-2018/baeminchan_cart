package codesquad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class CartItemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer count;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    private boolean deleted = false;

    public static CartItemLog from(User user, CartItem cartItem) {
        if (user != null && user.getId() == null) {
            user = null;
        }
        return new CartItemLog(null, user, cartItem.getProduct(), cartItem.getCount(), false);
    }

    @Builder
    public CartItemLog(Long id, User user, Product product, Integer count, boolean deleted) {
        this.id = id;
        this.user = user;
        this.count = count;
        this.product = product;
        this.deleted = deleted;
    }
}

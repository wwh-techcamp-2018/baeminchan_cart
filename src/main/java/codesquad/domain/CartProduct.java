package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Where(clause = "deleted != 'true'")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cart_user"))
    private User user;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cart_product"))
    private Product product;

    @Column
    private BigInteger count;

    @Column(name = "deleted")
    private boolean isDeleted;

    @Column
    private LocalDateTime createTime;

    public CartProduct(User user, Product product, BigInteger count) {
        this.user = user;
        this.product = product;
        this.count = count;
        this.createTime = LocalDateTime.now();
    }

    public BigInteger calculateTotalSellingPrice() {
        double discountRate = product.getDiscountRate();
        if (count.compareTo(new BigInteger("10")) >= 0 && discountRate < 0.2) {
            discountRate += 0.05;
        }

        long sellingPrice = this.product.getPrice() - (long)(this.product.getPrice() * discountRate);

        BigInteger totalSellingPrice = new BigInteger(String.valueOf(sellingPrice)).multiply(count);
        if (totalSellingPrice.compareTo(new BigInteger("40000")) < 0) {
            totalSellingPrice = totalSellingPrice.add(new BigInteger("2500"));
        }

        return totalSellingPrice;
    }

    public void delete() {
        isDeleted = true;
    }
}

package codesquad.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Objects;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cartitem_product"))
    private Product product;

    @DecimalMin(value = "0")
    private Integer count = 0;

    @DecimalMin(value = "0")
    private Long discountRate = 0L;

    @DecimalMin(value = "0")
    private Long price = 0L;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cartitem_cart"))
    private Cart cart;

    private boolean deleted = false;

    public CartItem() {
    }

    public CartItem(Product product, @DecimalMin(value = "0") Integer count, Cart cart) {
        this.product = product;
        this.count = count;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

    public Long getDiscountRate() {
        return discountRate;
    }

    public Long getPrice() {
        return price;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setCount(Integer count) {
        this.count = count;
        setPrice(product.getPrice() * this.count);
    }

    public void setDiscountRate(Long discountRate) {
        this.discountRate = discountRate;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(getId(), cartItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduct(), getCount(), getDiscountRate(), getPrice(), getCart(), isDeleted());
    }
}

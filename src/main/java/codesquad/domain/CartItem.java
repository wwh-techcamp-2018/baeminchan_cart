package codesquad.domain;

import codesquad.support.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class CartItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    @Size(min = 1)
    private Long quantity;

    @Column
    private boolean bought = false;

    @Column
    private boolean deleted = false;

    public CartItem() {
        this(null, null, null);
    }

    public CartItem(Product product, User user, @Size(min = 1) Long quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.bought = false;
        this.deleted = false;
    }

    public CartItem(Product product, User loginUser) {
        this(product, loginUser, 0L);
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public Long getQuantity() {
        return quantity;
    }

    public boolean isBought() {
        return bought;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void addQuantity(Long quantity) {
        this.quantity += quantity;
    }
}

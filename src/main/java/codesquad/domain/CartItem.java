package codesquad.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;
    //실제 존재하는 상품에 대한 연관관계 가짐
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    //실제 유저에 대한 연관관계 가짐
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    //실제 카트에 있는 상품을 구매하는 경우 true로 변경
    private boolean isBuy = false;
    //취소되는 경우 isCancel true로 변경
    private boolean isCancel = false;
    private int amount = 0;

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return isBuy == cartItem.isBuy &&
                Objects.equals(id, cartItem.id) &&
                Objects.equals(product, cartItem.product) &&
                Objects.equals(user, cartItem.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user, isBuy);
    }
}

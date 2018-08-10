package codesquad.domain;

import codesquad.support.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Cart extends AbstractEntity {
    public static final Cart EMPTY_CART = new EmptyCart();
    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    @Cascade(CascadeType.ALL)
    private List<CartProduct> cartProducts;
    //hint user가 null인 경우가 많다면(비회원주문) 일대일 조인 테이블로 따로 빼도 좋을듯함

    @JsonIgnore
    @OneToOne
    private User user;

    //todo  ProductService - List<cartItems>의 갯수가 0이 되면, Cart 삭제, CartHistory Insert

    @Transient
    private int cartProductCnt;

    @JsonIgnore
    public boolean isEmptyCart() {
        return false;
    }
    private static class EmptyCart extends Cart {
        @Override
        public boolean isEmptyCart() {
            return true;
        }
    }
    public void addCartProduct(CartProduct cartProduct){
        this.cartProducts.add(cartProduct);
        cartProduct.setCart(this);
        cartProductCnt++;
    }
}

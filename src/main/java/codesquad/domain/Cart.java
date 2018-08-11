package codesquad.domain;

import codesquad.support.AbstractEntity;
import codesquad.support.PriceCalcultor;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.*;
import java.util.stream.Collectors;


@Data
@EqualsAndHashCode(exclude = "cartProducts")
@Entity
public class Cart extends AbstractEntity {
    public static final Cart EMPTY_CART = new EmptyCart();

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    @Cascade(CascadeType.ALL)
    //hint - 중복 허용 X, insertion 순서대로 유지
    private List<CartProduct> cartProducts = new ArrayList<>();
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

    @JsonAnyGetter
    public Map getPrice(PriceCalcultor priceCalcultor) {
        Map<String, Long> calculation = new HashMap();
        Long totalPrice = cartProducts.stream().mapToLong(CartProduct::getTotalPrice).sum();//.reduce( (x, y) -> x+y );
        Long deliveryTotalPrice = priceCalcultor.calculateTotalPrice(totalPrice);
        calculation.put("totalPrice",totalPrice);
        calculation.put("deliveryTotalPrice", deliveryTotalPrice);

        return calculation;
    }

    private static class EmptyCart extends Cart {
        @Override
        public boolean isEmptyCart() {
            return true;
        }
    }
    public void addCartProduct(CartProduct cartProduct){
        cartProduct.setCart(this);
        if(cartProducts.contains(cartProduct)) {
            cartProducts.set(cartProducts.indexOf(cartProduct), cartProduct);
            return;
        }
        this.cartProducts.add(cartProduct);
        cartProductCnt++;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartProducts=" +
                cartProducts.stream().map(x-> x.getId()).collect(Collectors.toList()) +
                ", user=" + user +
                ", cartProductCnt=" + cartProductCnt +
                '}';
    }
}

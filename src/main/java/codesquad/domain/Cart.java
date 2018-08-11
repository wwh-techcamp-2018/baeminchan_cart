package codesquad.domain;

import codesquad.support.AbstractEntity;
import codesquad.support.PriceCalcultor;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Data
@EqualsAndHashCode(exclude = "cartProducts")
@Entity
@Slf4j
public class Cart extends AbstractEntity {
    public static final Cart EMPTY_CART = new EmptyCart();

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    @Cascade(CascadeType.ALL)
    //hint - 중복 허용 X, insertion 순서대로 유지
    private List<CartProduct> cartProducts = new ArrayList<>();
    //private Map<Long, CartProduct> cartProductsMap = new LinkedHashMap<>();
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

    @PostLoad
    public void initCartProductCnt() {
        this.cartProductCnt = cartProducts.size();
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
        log.debug("cartProduct {} {}", cartProduct, cartProduct.hashCode());

        //fixme cart, product가 동일하지 않은가? equals를 오버라이딩했지만 원하는대로 작동하지않는다ㅜㅜ
/*
        if(cartProducts.contains(cartProduct)) {
            cartProducts.set(cartProducts.indexOf(cartProduct), cartProduct);
            return;
        }
*/
//todo 리팩토링 - 코드 정리 또는 List > Map 으로 바꾸기
        Optional<CartProduct> duplicate = cartProducts.stream()
                .filter(x -> x.getProduct().getId().equals(cartProduct.getProduct().getId())).findFirst();
        if (duplicate.isPresent()){
            log.debug(" indexOf {}", cartProducts.indexOf(duplicate.get()));
            cartProducts.get(cartProducts.indexOf(duplicate.get())).setCount(cartProduct.getCount());
            // fixme 자주하는 실수 ㅠㅠ 반드시 복습
            // cartProducts.set(cartProducts.indexOf(duplicate.get()), cartProduct);
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

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
    @OneToMany(mappedBy = "cart")//, fetch=FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @OrderBy("created_at")
    private List<CartProduct> cartProducts = new ArrayList<>();
    //private Map<Long, CartProduct> cartProductsMap = new LinkedHashMap<>();

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


    public void initCartProductCnt() {
        this.cartProductCnt = getCartProducts().size();//cartProducts.size();
        log.debug("initCartProductCnt {}", cartProductCnt);
    }

    @PostPersist
    public void postPersist(){
        log.debug("postPersist {}", cartProducts.size());
        initCartProductCnt();
    }
    @PostLoad
    public void postLoad(){
        log.debug("PostLoad {}", cartProducts.size());
        initCartProductCnt();
    }
    @PostUpdate
    public void postUpdate(){
        log.debug("postUpdate {}", cartProductCnt);
        initCartProductCnt();
    }

    @PrePersist
    public void PrePersist(){
        log.debug("PrePersist {}", cartProductCnt);
        initCartProductCnt();
    }
    @PreUpdate
    public void preUpdate(){
        log.debug("preUpdate {}", cartProductCnt);
        initCartProductCnt();
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
        //todo 리팩토링 - 코드 정리 또는 List > Map 으로 바꾸기
        Optional<CartProduct> duplicate = cartProducts.stream()
                .filter(x -> x.getProduct().getId().equals(cartProduct.getProduct().getId())).findFirst();
        if (duplicate.isPresent()){
            duplicate.get().changeCountBy(cartProduct);
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

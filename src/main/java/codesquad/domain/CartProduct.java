package codesquad.domain;

import codesquad.dto.CartProductDTO;
import codesquad.support.AbstractEntity;
import codesquad.support.PriceCalcultor;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
//todo @DATA 공부
@NoArgsConstructor

//todo builder 삭제
@Builder @AllArgsConstructor
@Entity
@Slf4j
public class CartProduct extends AbstractEntity{
    @ManyToOne(optional = false)
    @NotNull
    @ToString.Exclude
    private Cart cart;

    @ManyToOne(optional = false)
    @NotNull
    @ToString.Exclude
    private Product product;

    @Column(nullable = false)
    int count = 1;

    @Transient
    @DecimalMin(value = "0")
    Long totalPrice = 0L;


    public CartProduct(PriceCalcultor calcultor){

    }
    public CartProduct(CartProductDTO dto){
        this.product = dto.getProduct();
        this.count = dto.getCount();
        this.totalPrice = dto.getTotalPrice();
        this.setCart(dto.getCart());

    }
    public void setCart(@NotNull Cart cart){
        this.cart = cart;
        this.cart.addCartProduct(this);
    }
//hint priceCalculator
    public CartProduct(@NotNull Cart cart, @NotNull Product product, int count, PriceCalcultor priceCalcultor) {
        this.cart = cart;
        this.product = product;
        this.count = count;
        this.totalPrice = product.calculatePrice(priceCalcultor, this.count);
    }
    @PostLoad
    @PrePersist @PreUpdate
    public void initTotalPrice() {
        this.totalPrice =  product.calculatePrice(PriceCalcultor.getInstance(), this.count);
        log.debug(" initTotalPrice called CartProduct {} ",totalPrice);
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    /*
        //hint Product, Cart 가 같은 경우, 같은 CartProduct - 식별키
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            //todo 질문하기 - contains & equals를 쓰지 않는다?
            //if (!super.equals(o)) return false;
            CartProduct that = (CartProduct) o;
            return
                    Objects.equals(cart, that.cart) &&
                    Objects.equals(product, that.product) ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), cart, product);
        }
    */
    public void changeCountBy(CartProduct cartProduct) {
        this.count += cartProduct.count;
    }
}

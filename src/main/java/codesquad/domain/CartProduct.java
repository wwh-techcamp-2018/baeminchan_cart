package codesquad.domain;

import codesquad.dto.CartProductDTO;
import codesquad.support.AbstractEntity;
import codesquad.support.PriceCalcultor;
import lombok.*;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import java.util.Objects;

@Data
//todo @DATA 공부
@NoArgsConstructor

//todo builder 삭제
@Builder @AllArgsConstructor
@Entity
public class CartProduct extends AbstractEntity{
    @ManyToOne(optional = false)
    private Cart cart;
    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    int count = 1;

    @Transient
    @DecimalMin(value = "0")
    Long totalPrice;


    public CartProduct(PriceCalcultor calcultor){
        //todo DiscountPriceCalcultor에게 위임
        this.totalPrice = count * product.getPrice();
    }
    public CartProduct(CartProductDTO dto){
        this.cart = dto.getCart();
        this.product = dto.getProduct();
        this.count = dto.getCount();
        this.totalPrice = dto.getTotalPrice();
    }
    public CartProduct(Cart cart, Product product, int count) {

        this.cart = cart;
        this.product = product;
        this.count = count;
//hint product === null?
        this.totalPrice = product.getPrice() * count;
    }
//hint priceCalculator
    public CartProduct(Cart cart, Product product, int count, PriceCalcultor priceCalcultor) {
        this(priceCalcultor);
        this.cart = cart;
        this.product = product;
        this.count = count;
    }

    //hint Product, Cart 가 같은 경우, 같은 CartProduct - 식별키
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartProduct that = (CartProduct) o;
        return
                Objects.equals(cart, that.cart) &&
                Objects.equals(product, that.product) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), cart, product);
    }
}

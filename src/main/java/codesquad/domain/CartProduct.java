package codesquad.domain;

import codesquad.support.AbstractEntity;
import codesquad.support.PriceCalcultor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;

@Getter
@Setter

@Entity
public class CartProduct extends AbstractEntity {
    @ManyToOne(optional = false)
    private Cart cart;
    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    int count = 1;

    @Transient
    @DecimalMin(value = "0")
    Long totalPrice;

    public CartProduct(){
    }
    public CartProduct(PriceCalcultor calcultor){
        //todo DiscountPriceCalcultor에게 위임
        this.totalPrice = count * product.getPrice();
    }

    public CartProduct(Cart cart, Product product, int count) {
        this();
        this.cart = cart;
        this.product = product;
        this.count = count;
        this.totalPrice = product.getPrice() * count;
    }
//hint priceCalculator
    public CartProduct(Cart cart, Product product, int count, PriceCalcultor priceCalcultor) {
        this(priceCalcultor);
        this.cart = cart;
        this.product = product;
        this.count = count;
    }
}

package codesquad.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CartTest {

    @Test
    public void add() {
        Cart cart = new Cart();
        cart.add(Product.defaultProduct()).add(Product.defaultProduct());
        assertThat(cart.getProductSets().size()).isEqualTo(1);
    }

    @Test
    public void add1() {
        Cart cart = new Cart();
        ProductSet pset = new ProductSet(Product.defaultProduct());
        cart.add(pset);
        assertThat(cart.getProductSets().size()).isEqualTo(1);
    }
}
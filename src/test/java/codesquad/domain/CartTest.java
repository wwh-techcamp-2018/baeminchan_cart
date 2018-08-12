package codesquad.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CartTest {
    private Product product;
    private CartProduct cartProduct;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        product = new Product(1L, "된장찌개", "맛있는 된장", "/img/1", 5000L);
        cartProduct = new CartProduct(product, 1);
        cart = new Cart();
    }

    @Test
    public void 카트_반찬_추가() {
        cart.addCartProduct(cartProduct);
        log.debug("cart.getCartProducts() : {}", cart.getCartProducts());
        assertThat(cart.getCartProducts()).contains(cartProduct);
    }

    @Test
    public void 카트_반찬_갯수_변경() {
//        cart.updateCount(2);
//        assertThat(cart.getCount()).isEqualTo(2);
    }

    @Test
    public void 카트_반찬_제거() {
//        cart.delete();
//        assertThat(cart.isDeleted()).isTrue();

    }


}
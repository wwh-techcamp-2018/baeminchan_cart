package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {
    private Product product;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        product = new Product(1L, "된장찌개", "맛있는 된장", "/img/1", 5000L);
        cart = new Cart(product, 1);
    }

    @Test
    public void 카트_반찬_추가() {
        assertThat(cart.getProduct().getTitle()).isEqualTo("된장찌개");
    }

    @Test
    public void 카트_반찬_갯수_변경() {
        cart.updateCount(2);
        assertThat(cart.getCount()).isEqualTo(2);
    }

    @Test
    public void 카트_반찬_제거() {
        cart.delete();
        assertThat(cart.isDeleted()).isTrue();

    }


}
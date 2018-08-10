package codesquad.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingBasketTest {

    private ShoppingBasket shoppingBasket;

    @Before
    public void setUp() throws Exception {
        shoppingBasket = new ShoppingBasket();
    }

    @Test
    public void put_성공() {
        Product product = new Product("hong","hong","", 1000L);
        Merchandise merchandise = new Merchandise(1, true, product);
        shoppingBasket.put(merchandise);
        shoppingBasket.put(merchandise);
        Map<Merchandise, Integer> map = shoppingBasket.getMerchandises();
        assertThat(map.get(merchandise)).isEqualTo(2);
    }
}
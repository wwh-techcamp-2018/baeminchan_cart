package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class OrderTest {

    private Order order;

    @Before
    public void setUp(){
        order = new Order();
    }


    @Test
    public void deliveryFeeNeedCaseTest(){
        long price = 39999;
        Product needCaseProduct = new Product(price, 0);
        order.addCart(new Cart(needCaseProduct, 1));
        assertThat(order.getTotalPrice()).isGreaterThan(price);

    }

    @Test
    public void deliveryFeeDontNeedCaseTest(){
        long price = 40000;
        Product dontNeedCaseProduct = new Product(price,0);
        order.addCart(new Cart(dontNeedCaseProduct, 1));
        assertThat(order.getTotalPrice()).isEqualTo(price);
    }
}

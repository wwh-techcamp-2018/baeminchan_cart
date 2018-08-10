package codesquad.domain;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void test() {
        List<Cart> cartList = cartRepository.findAll();
        cartList.get(0);
    }
}

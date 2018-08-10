package codesquad.service;

import codesquad.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartLogServiceTest {

    @Mock
    private CartItemLogRepository cartItemLogRepository;

    @InjectMocks
    private CartLogService cartLogService;

    @Test
    public void addCartLog() {
        int count = 3;
        User user = User.builder().id(1L).build();
        Product product = Product.builder().id(1L).build();
        CartItemLog expected = CartItemLog.builder().user(user).product(product).count(count).build();

        when(cartItemLogRepository.save(any())).thenReturn(expected);

        CartItemLog result = cartLogService.addCartLog(user, new CartItem(product, count));

        assertThat(result.getProduct()).isEqualTo(expected.getProduct());
        assertThat(result.getUser()).isEqualTo(expected.getUser());
        assertThat(result.getCount()).isEqualTo(expected.getCount());
    }
}
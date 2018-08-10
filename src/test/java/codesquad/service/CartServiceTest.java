package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void get() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(Cart.CART1));
        Cart newCart = cartService.get(1L);
        assertThat(newCart).isEqualTo(Cart.CART1);
    }

}

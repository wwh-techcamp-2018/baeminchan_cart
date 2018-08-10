package codesquad.service;

import codesquad.domain.CartItem;
import codesquad.domain.CartItemRepository;
import codesquad.domain.Product;
import codesquad.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartItemServiceTest {

    Product product;
    User user;
    CartItem cartItem;

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartItemService cartItemService;

    @Before
    public void setUp(){
        product = new Product(1L);
        user = new User(1L, "javajigi@tech.com", "12345678", "javajigi", "010-1234-5678");
        cartItem = new CartItem(product, user, 1L);
    }

    @Test
    public void findByUserIdAndProductIdTest(){
        when(cartItemRepository.findByUserIdAndProductId(1L, 1L)).thenReturn(Optional.of(cartItem));
        assertThat(cartItemService.findByUserIdAndProductId(1L,1L)).isEqualTo(cartItem);
    }

    @Test
    public void findByUserIdAndProductIdTest_fail(){
        when(cartItemRepository.findByUserIdAndProductId(1L, 1L)).thenReturn(Optional.of(cartItem));
        assertThat(cartItemService.findByUserIdAndProductId(1L,2L)).isEqualTo(null);
    }
}
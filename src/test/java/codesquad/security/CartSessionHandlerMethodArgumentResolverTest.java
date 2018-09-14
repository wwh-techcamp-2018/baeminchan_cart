package codesquad.security;

import codesquad.domain.Cart;
import codesquad.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartSessionHandlerMethodArgumentResolverTest {
    @Mock
    private MethodParameter parameter;

    @Mock
    private NativeWebRequest request;

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartSessionHandlerMethodArgumentResolver cartSessionHandlerMethodArgumentResolver;

    @Test
    public void get_session_cart() throws Exception {
        Cart cart = Cart.builder().build();
        when(request.getAttribute(SessionUtils.CART_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(cart);

        Cart cartSession = (Cart) cartSessionHandlerMethodArgumentResolver
                .resolveArgument(parameter, null, request, null);

        assertThat(cartSession).isEqualTo(cart);
    }

    @Test
    public void get_session_no_cart() throws Exception {
        Cart cart = Cart.builder().build();
        when(request.getAttribute(SessionUtils.CART_SESSION_KEY, WebRequest.SCOPE_SESSION)).thenReturn(null);
        when(cartService.create(any())).thenReturn(cart);

        Cart cartSession = (Cart) cartSessionHandlerMethodArgumentResolver
                .resolveArgument(parameter, null, request, null);

        assertThat(cartSession).isEqualTo(cart);

    }
}

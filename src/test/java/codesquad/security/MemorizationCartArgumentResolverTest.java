package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.MemorizationCart;
import org.junit.Before;
import org.junit.Test;


import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class MemorizationCartArgumentResolverTest {


    private MemorizationCartArgumentResolver resolver;

    @Before
    public void setUp() throws Exception {
        resolver = new MemorizationCartArgumentResolver();
    }

    @Test
    public void supportsParameter(){
        MethodParameter mockMethodParameter = mock(MethodParameter.class);
        when(mockMethodParameter.hasParameterAnnotation(MemorizationCart.class)).thenReturn(true);
        assertThat(resolver.supportsParameter(mockMethodParameter)).isEqualTo(true);
    }

    @Test
    public void resolveArgument() throws Exception {
        Cart cart = new Cart();
        NativeWebRequest mockNativeWebRequest = mock(NativeWebRequest.class);
        when(mockNativeWebRequest.getAttribute(SessionUtils.CART_KEY,NativeWebRequest.SCOPE_SESSION))
                .thenReturn(cart);

        assertThat(resolver.resolveArgument(null,null,mockNativeWebRequest,null))
                .isEqualTo(cart);
    }

}
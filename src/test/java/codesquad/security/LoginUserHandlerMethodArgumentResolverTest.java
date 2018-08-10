package codesquad.security;

import codesquad.domain.User;
import codesquad.exception.UnAuthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoginUserHandlerMethodArgumentResolverTest {

    @Mock
    private MethodParameter parameter;

    @Mock
    private NativeWebRequest request;


    @InjectMocks
    private LoginUserHandlerMethodArgumentResolver resolver;

    @Test
    public void supportsParameter() {
        when(parameter.hasParameterAnnotation(LoginUser.class)).thenReturn(true);
        assertThat(resolver.supportsParameter(parameter)).isEqualTo(true);
    }

    @Test
    public void supportsParameter_false() {
        when(parameter.hasParameterAnnotation(LoginUser.class)).thenReturn(false);
        assertThat(resolver.supportsParameter(parameter)).isEqualTo(false);
    }

    @Test
    public void resolveArgument() throws Exception {
        User user = new User(1L, "rlatnghd95@gmail.com", "asdqwe123", "asd", "010-1010-1010");

        when(request.getAttribute(SessionUtils.USER_SESSION_KEY, WebRequest.SCOPE_SESSION))
                .thenReturn(user);

        User loginUser = (User) resolver.resolveArgument(parameter,null,request  , null);
        assertThat(loginUser).isEqualTo(user);
    }


    @Test (expected = UnAuthorizedException.class)
    public void notLoginResolveArgument() throws Exception {
        resolver.resolveArgument(parameter,null,request  , null);
    }
}
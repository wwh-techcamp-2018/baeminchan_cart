package codesquad.security;

import codesquad.domain.User;
import codesquad.exception.UnAuthorizedException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = SessionUtils.getUserFromSession(webRequest);

        LoginUser loginUser = parameter.getParameterAnnotation(LoginUser.class);
        if (user == null || !loginUser.required()) {
            throw new UnAuthorizedException("You're required Login!");
        }

        return user;
    }
}


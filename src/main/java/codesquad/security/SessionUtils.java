package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    private static final String SESSION_CART = "SESSION_CART";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static Cart getCartInSession(NativeWebRequest webRequest) {
        return Optional.ofNullable((Cart) webRequest.getAttribute(SESSION_CART, WebRequest.SCOPE_SESSION)).orElse(new Cart());
    }

    public static void setCartInSession(HttpSession session, Cart cart) {
        session.setAttribute(SESSION_CART, cart);
    }

}

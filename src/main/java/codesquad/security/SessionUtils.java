package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "cart";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static Optional<User> getUserFromSession(NativeWebRequest webRequest) {
        return Optional.ofNullable((User) webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION));
    }

    public static void setCartInSession(HttpSession session, Cart cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public static Optional<Cart> getCartFromSession(NativeWebRequest webRequest) {
        return Optional.ofNullable((Cart) webRequest.getAttribute(CART_SESSION_KEY, WebRequest.SCOPE_SESSION));
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static boolean isCart(HttpSession session) {
        return session.getAttribute(CART_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

}

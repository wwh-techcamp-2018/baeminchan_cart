package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_KEY = "memorizationCart";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static void setCartInSession(HttpSession session, Cart cart) {
        session.setAttribute(CART_KEY, cart);
    }
    public static boolean isMemorizationCart(HttpSession session) {
        return session.getAttribute(CART_KEY) != null;
    }

    public static boolean isMemorizationCart(NativeWebRequest request) {
        Object object = request.getAttribute(CART_KEY, WebRequest.SCOPE_SESSION);
        return object != null;
    }

    public static Cart getCartInSession(NativeWebRequest request) {
        return (Cart)request.getAttribute(CART_KEY, WebRequest.SCOPE_SESSION);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
    public static void removeCartInSession(HttpSession session) {
        session.removeAttribute(CART_KEY);
    }
    public static Cart getCartInSession(HttpSession session) {
        return (Cart)session.getAttribute(CART_KEY);
    }
}

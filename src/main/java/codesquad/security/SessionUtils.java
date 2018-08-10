package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "LOGINED_USER";
    public static final String CART_SESSION_KEY = "CART";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session) {
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static boolean hasCart(HttpSession session) {
        return session.getAttribute(CART_SESSION_KEY) != null;
    }

    public static Cart getCartOrCreateIfNotExist(HttpSession session) {
        if (!hasCart(session)) {
            session.setAttribute(CART_SESSION_KEY, new Cart());
        }
        return (Cart) session.getAttribute(CART_SESSION_KEY);
    }
}

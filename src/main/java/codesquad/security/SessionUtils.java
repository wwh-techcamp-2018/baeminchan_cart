package codesquad.security;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "cart";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static boolean hasCart(HttpSession session) { return getCartInSession(session) != null; }

    public static void setCartInSession(HttpSession session, Long cartId) {
        session.setAttribute(CART_SESSION_KEY, cartId);
    }

    public static Long getCartInSession(HttpSession session) {
        return (Long) session.getAttribute(CART_SESSION_KEY);
    }
}

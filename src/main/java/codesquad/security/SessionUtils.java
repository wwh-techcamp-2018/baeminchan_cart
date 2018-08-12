package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "cartUser";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static void setCartUserInSession(HttpSession session) {
        Cart cart = new Cart();
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public static Cart getCartUserInSession(HttpSession session) {
        return (Cart) session.getAttribute(CART_SESSION_KEY);
    }


    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static boolean isCartUser(HttpSession session) {
        return session.getAttribute(CART_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
}

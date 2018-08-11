package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    private static final String USER_CART_KEY = "user_cart_key";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session) {
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static void saveCart(HttpSession session, Cart cart) {
        session.setAttribute(USER_CART_KEY, cart);
    }

    public static Cart getCarts(HttpSession session) {
        return Optional.ofNullable((Cart) session.getAttribute(USER_CART_KEY)).orElseGet(Cart::new);
    }

    public static int getCartSize(HttpSession session) {
        return Optional.ofNullable((Cart) session.getAttribute(USER_CART_KEY)).orElseGet(Cart::new).getItemSize();
    }
}

package codesquad.utils;

import javax.servlet.http.HttpSession;

public class CartUtils {
    public static final String CART_SESSION_KEY = "cartList";

    public static boolean isEmptyCart(HttpSession session) {
        return session.getAttribute(CART_SESSION_KEY) == null;
    }
}

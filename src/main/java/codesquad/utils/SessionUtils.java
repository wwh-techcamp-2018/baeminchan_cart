package codesquad.utils;

import codesquad.domain.Cart;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "cartUser";

    public static Cart getCartFromSession(HttpSession session){
        Cart cart = (Cart) session.getAttribute(CART_SESSION_KEY);
        if(cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
}

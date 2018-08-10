package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;
import codesquad.dto.CartDTO;
import codesquad.dto.CartDTO2;

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

//    public static void setCartInSession(HttpSession session, CartDTO cart) {
//        session.setAttribute(USER_SESSION_KEY, cart);
//    }
//
//    public static boolean hasCart(HttpSession session) { return session.getAttribute(CART_SESSION_KEY) != null; }
//
//    public static CartDTO getCartInSession(HttpSession session) {
//        return (CartDTO) session.getAttribute(CART_SESSION_KEY);
//    }

    public static void setCartInSession(HttpSession session, CartDTO2 cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public static boolean hasCart(HttpSession session) { return session.getAttribute(CART_SESSION_KEY) != null; }

    public static CartDTO2 getCartInSession(HttpSession session) {
        return (CartDTO2) session.getAttribute(CART_SESSION_KEY);
    }
}

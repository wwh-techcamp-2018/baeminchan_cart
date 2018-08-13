package codesquad.security;

import codesquad.domain.ShoppingBasket;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String SHOPPING_CART = "shoppingCart";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static void setShoppingCartInSession(HttpSession session) {
        session.setAttribute(SHOPPING_CART, new ShoppingBasket());
    }

    public static ShoppingBasket getShoppingCartInSession(HttpSession session) {
        if(!SessionUtils.isShoppingCart(session)) {
            SessionUtils.setShoppingCartInSession(session);
        }
        return (ShoppingBasket) session.getAttribute(SHOPPING_CART);
    }

    public static boolean isShoppingCart(HttpSession session) {
        return session.getAttribute(SHOPPING_CART) != null;
    }

    public static void removeShoppingCartInSession(HttpSession session){
        session.removeAttribute(SHOPPING_CART);
    }
}

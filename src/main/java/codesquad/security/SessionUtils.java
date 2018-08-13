package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.Order;
import codesquad.domain.Product;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSSION_KEY = "cart";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static Order addProductInSessionCart(HttpSession session, Product product, int amount){
        Order order = (Order)session.getAttribute(CART_SESSSION_KEY);
        order.addCart(new Cart(product, amount));
        session.setAttribute(CART_SESSSION_KEY, order);
        return order;
    }

    public static Order deleteProductInSessionCart(HttpSession session, Product product, int amount){
        Order order = (Order)session.getAttribute(CART_SESSSION_KEY);
        order.removeProduct(product);
        return order;
    }
}

package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;
import org.springframework.web.context.request.WebRequest;

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

    public static Cart getCart(WebRequest session) {
        Cart cart = (Cart) session.getAttribute(CART_SESSION_KEY, WebRequest.SCOPE_SESSION);

        if(cart == null){
            cart = Cart.create();
            session.setAttribute(CART_SESSION_KEY, cart, WebRequest.SCOPE_SESSION);
        }

        return cart;
    }
}

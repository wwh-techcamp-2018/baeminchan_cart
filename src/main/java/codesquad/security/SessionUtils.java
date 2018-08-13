package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.Category;
import codesquad.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "cartSession";
    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static User getUserFromSession(HttpSession session){
        return Optional.ofNullable((User) session.getAttribute(USER_SESSION_KEY)).orElse(User.GUEST_USER);
    }

    //todo get,setCartInSession

    public static void setCartInSession(HttpSession session, Cart cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public static Cart getCartFromSession(HttpSession session){
        return Optional.ofNullable((Cart) session.getAttribute(CART_SESSION_KEY)).orElse(Cart.EMPTY_CART);
    }
    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
}

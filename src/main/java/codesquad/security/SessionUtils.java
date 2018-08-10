package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "viewerCart";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static void setCartInSession(HttpSession session) {
        session.setAttribute(CART_SESSION_KEY, new Cart());
    }

    public static Cart getCartInSession(HttpSession session) {
        if(session.getAttribute(CART_SESSION_KEY) == null) SessionUtils.setCartInSession(session);
        return (Cart) session.getAttribute(CART_SESSION_KEY);
    }

}

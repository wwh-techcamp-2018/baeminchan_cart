package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "cartUser";

    public static List<Cart> getCartsFromSession(HttpSession session){
        List<Cart> carts = (ArrayList) session.getAttribute(CART_SESSION_KEY);
        if(carts == null) {
            carts = new ArrayList<>();
        }
        return carts;
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

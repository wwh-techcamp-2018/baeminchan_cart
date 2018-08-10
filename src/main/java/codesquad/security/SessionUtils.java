package codesquad.security;

import codesquad.domain.User;
import codesquad.dto.CartProductDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_SESSION_KEY = "cartItems";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

    public static void setCartInSession(HttpSession session, CartProductDTO productDTO) {
        List<CartProductDTO> cartList = new ArrayList<>();
        if(isCartExisted(session))
            cartList = (ArrayList)session.getAttribute(CART_SESSION_KEY);
        cartList.add(productDTO);
        session.setAttribute(CART_SESSION_KEY, cartList);
    }

    public static boolean isCartExisted(HttpSession session) {
        return session.getAttribute(CART_SESSION_KEY) != null;
    }

    public static void removeCartInSession(HttpSession session){
        session.removeAttribute(CART_SESSION_KEY);
    }
}

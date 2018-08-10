package codesquad.security;

import codesquad.domain.User;
import codesquad.dto.CartDto;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String USER_SESSION_CART_KEY = "cartKey";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }

    public static List<CartDto> getCartDtoFromSession(HttpSession session) {
        List<CartDto> cartDtos = (ArrayList) session.getAttribute(USER_SESSION_CART_KEY);
        if (cartDtos == null) cartDtos = new ArrayList<>();
        return cartDtos;
    }

    public static void cleanSessionCart(HttpSession session) {
        session.setAttribute(USER_SESSION_CART_KEY, null);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
}

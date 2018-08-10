package codesquad.security;

import codesquad.domain.Cart;
import codesquad.domain.CartItem;
import codesquad.domain.Product;
import codesquad.domain.User;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";
    public static final String CART_KEY = "cart";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static Optional<User> getUserFromSession(HttpSession session) {
        return Optional.ofNullable((User) session.getAttribute(USER_SESSION_KEY));
    }

    public static Optional<User> getUserFromSession(NativeWebRequest webRequest) {
        return Optional.ofNullable((User) webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION));
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }


    public static void addCartItemInSession(HttpSession session, CartItem cartItem) {
        Cart cart = getCartInSession(session).orElse(new Cart());
        cart.addCartItem(cartItem);
        session.setAttribute(CART_KEY, cart);
    }

    public static Optional<Cart> getCartInSession(HttpSession session) {
        return Optional.ofNullable((Cart) session.getAttribute(CART_KEY));
    }
}

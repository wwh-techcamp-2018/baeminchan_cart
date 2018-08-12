package codesquad.service;

import codesquad.domain.Cart;
import codesquad.security.SessionUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Service
public class CartService {

    public LinkedHashMap<Long, Integer> getProducts(HttpSession session) {
        Cart cart = SessionUtils.getCartInSession(session);
        return cart.getProducts();
    }

    public void addProduct(HttpSession session, Long productId, Integer count) {
        Cart cart = SessionUtils.getCartInSession(session);
        cart.addProduct(productId, count);
        SessionUtils.setCartInSession(session, cart);
    }

    public void updateProduct(HttpSession session, Long productId, Integer count) {
        Cart cart = SessionUtils.getCartInSession(session);
        cart.updateProduct(productId, count);
        SessionUtils.setCartInSession(session, cart);
    }

    public void deleteProduct(HttpSession session, Long productId, Integer count) {
        Cart cart = SessionUtils.getCartInSession(session);
        cart.deleteProduct(productId, count);
        SessionUtils.setCartInSession(session, cart);
    }

}

package codesquad.service;

import codesquad.domain.Cart;
import codesquad.security.SessionUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class CartService {

    public void addProduct(HttpSession session, Long productId, int count) {
        Cart cart = SessionUtils.getCartInSession(session);
        cart.addProduct(productId, count);
        SessionUtils.setCartInSession(session, cart);
    }

    public Map<Long, Integer> getProducts(HttpSession session) {
        Cart cart = SessionUtils.getCartInSession(session);
        return cart.getProducts();
    }

    public void deleteProduct(HttpSession session, Long productId, int count) {
        Cart cart = SessionUtils.getCartInSession(session);
        cart.deleteProduct(productId, count);
        SessionUtils.setCartInSession(session, cart);
    }

}

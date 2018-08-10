package codesquad.service;

import codesquad.domain.Cart;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartService {
    public void add(HttpSession session, CartProductDTO cartProductDTO) {
        Cart cart = SessionUtils.getCartOrCreateIfNotExist(session);
        cart.add(cartProductDTO);
    }

    public void update(HttpSession session, CartProductDTO cartProductDTO) {
        Cart cart = SessionUtils.getCartOrCreateIfNotExist(session);
        cart.update(cartProductDTO);
    }

    public void delete(HttpSession session, CartProductDTO cartProductDTO) {
        Cart cart = SessionUtils.getCartOrCreateIfNotExist(session);
        cart.delete(cartProductDTO);
    }
}

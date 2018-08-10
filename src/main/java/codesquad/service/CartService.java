package codesquad.service;

import codesquad.domain.Cart;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    public List<CartProductDTO> get(HttpSession session) {
        Cart cart = SessionUtils.getCartOrCreateIfNotExist(session);
        return cart.toProductDTOList();
    }

    public List<CartProductDTO> add(HttpSession session, CartProductDTO cartProductDTO) {
        Cart cart = SessionUtils.getCartOrCreateIfNotExist(session);
        cart.add(cartProductDTO);
        return cart.toProductDTOList();
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

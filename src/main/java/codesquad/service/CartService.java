package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> readCarts() {
        return cartRepository.findAll();
    }

    public Cart read(Long id) {
        return cartRepository.findById(id).get();
    }


    public Cart save(Cart cart) {
        return  cartRepository.save(cart);
    }

    public Cart update(Cart cart) {
        Cart originalCart = cartRepository.findById(cart.getId()).get();
        originalCart.update(cart);
        return cartRepository.save(originalCart);
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}

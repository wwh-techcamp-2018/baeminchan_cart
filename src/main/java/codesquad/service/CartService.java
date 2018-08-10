package codesquad.service;

import codesquad.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public Cart findById(Long id) { return cartRepository.findById(id).get(); }

    public Cart save() {
        return cartRepository.save(Cart.of());
    }

    public Cart save(Long cartId, CartItem cartItem) {
        Cart cart = findById(cartId);

        if(!cart.contains(cartItem)) {
            cart.add(cartItem);
        }

        return cartRepository.save(cart);
    }
}

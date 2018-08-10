package codesquad.service;

import codesquad.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService {

    @Resource
    private ProductRepository productRepository;

    @Resource
    private CartRepository cartRepository;

    public void putInCart(Cart cart, long productId){
        cart.add(productRepository.findById(productId).get());
        cartRepository.save(cart);
    }

//    @Transactional
//    public Cart mergeCart(User loginUser, Cart cart) {
//        Cart userCart = cartRepository.findByUser(loginUser).orElse(new Cart(loginUser));
//        return cartRepository.save(userCart.mergeCart(cart));
//    }
}

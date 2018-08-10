package codesquad.service;

import codesquad.domain.CartItem;
import codesquad.domain.CartItemRepository;
import codesquad.exception.NoCartItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService{

    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartItem> findByUserId(Long id) {
        return cartItemRepository.findByUserId(id);
    }

    public CartItem findByUserIdAndProductId(Long userId, Long productId){
        try {
            return cartItemRepository.findByUserIdAndProductId(userId, productId).orElseThrow(() -> new NoCartItemException());
        } catch (NoCartItemException e){
            return null;
        }
    }

    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
}

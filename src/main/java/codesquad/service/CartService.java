package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.domain.ProductRepository;
import codesquad.dto.CartDto;
import codesquad.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public Cart create() {
        return cartRepository.save(Cart.builder().build());
    }

    @Transactional
    public Cart add(Long cartId, Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("반찬이 존재하지 않습니다.");
        }
        Cart cart = findById(cartId);
        cart.addProduct(productId, 1);
        return cartRepository.save(cart);
    }
}

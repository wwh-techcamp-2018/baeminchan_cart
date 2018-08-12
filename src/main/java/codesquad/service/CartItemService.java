package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItem save(Cart cart, CartItemDto cartItemDto) {
        Product product = productRepository.findById(cartItemDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);

        CartItem cartItem = cart.getCartItem(product, cartItemDto.getQuantity());
        return cartItemRepository.save(cartItem);
    }
}

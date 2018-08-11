package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartItem;
import codesquad.domain.CartRepository;
import codesquad.domain.User;
import codesquad.dto.CartProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart save(CartProductDto dto, User loginUser) {
        Optional<Cart> maybeCart = cartRepository.findByUser(loginUser);

        if (!maybeCart.isPresent()) {
            return cartRepository.save(Cart.createOf(loginUser, dto.toEntity()));
        }

        return cartRepository.save(maybeCart.get().add(dto.toEntity()));
    }

    public List<CartItem> getProductList(User user) {
        return cartRepository.findByUser(user).orElseThrow(RuntimeException::new).getCartItems();
    }
}

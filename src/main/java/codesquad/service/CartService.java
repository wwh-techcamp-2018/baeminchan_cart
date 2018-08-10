package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.dto.CartDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartService {
    @Resource
    private CartRepository cartRepository;


    public void create(CartDto cartDto, Product product, User user) {
        cartRepository.save(cartDto.toCart(product, user));
    }

    public void addAll(List<Cart> carts) {
        if (carts.size() == 0) return;
        carts.forEach((cart) -> cartRepository.save(cart));
    }

    public Iterable<Cart> list(User buyer) {
        return cartRepository.findByBuyer(buyer);
    }
}

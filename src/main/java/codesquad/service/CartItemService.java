package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static javafx.beans.binding.Bindings.when;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartRepository;
    @Autowired
    private ProductService productService;

    @Transactional
    public CartItem save(User loginUser, CartItemDto cartItemDto) {
        Product product = productService.findById(cartItemDto.getProductId());
        //유저와 상품을 조회해 해당 상품이 이미 장바구니에 들어있으면, 해당 카트 아이템에 삽입한다.
        //없으면 새로운 장바구니 아이템을 생성한다.
        CartItem cartItem = cartRepository.findByUserAndProduct(loginUser, product)
                .orElseGet(() -> new CartItem());
        cartItem.setProduct(product);
        cartItem.setUser(loginUser);
        cartItem.addAmount(cartItemDto.getAmount());
        return this.cartRepository.save(cartItem);
    }
}

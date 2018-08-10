package codesquad.service;

import codesquad.domain.Basket;
import codesquad.domain.BasketRepository;
import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.dto.ProductDTO;
import codesquad.security.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

@Service("cartService")
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BasketRepository basketRepository;

    public Cart find(ProductDTO productDTO,HttpSession session) {

        Long cartId = SessionUtils.getShoppingCart(session).getId();
        Basket basket = basketRepository.findByCartIdAndProductIdAndDeletedFalse(cartId, productDTO.getId());

        log.info("basket : {}",basket);

        if (basket == null) {
            basketRepository.save(
                    basket.builder()
                            .cartId(cartId)
                            .productId(productDTO.getId())
                            .amount(productDTO.getAmount())
                            .build()
            );

        }else{

            basket.updateAmount(productDTO.getAmount());
            basketRepository.save(basket);

        }

        Cart cart =cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        SessionUtils.setCartInSession(session,cart);
        return cart;
    }

    public Cart makeCart(HttpSession session) {
        if (!SessionUtils.isLoginUser(session)) {
            return cartRepository.save(new Cart());
        }
        return cartRepository.save(Cart
                .builder()
                .id(SessionUtils.getUserInSession(session).getId())
                .build());

    }
}

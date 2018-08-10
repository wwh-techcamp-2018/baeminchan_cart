package codesquad.web;

import codesquad.domain.Cart;
import codesquad.dto.CartDTO;
import codesquad.dto.CartDTO2;
import codesquad.dto.CartItemPostDTO;
import codesquad.security.BasicAuthInterceptor;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private static final Logger log = LoggerFactory.getLogger(BasicAuthInterceptor.class);


    @Autowired
    private CartService cartService;


    @GetMapping
    public CartDTO2 show(HttpSession session) {
        if (SessionUtils.hasCart(session)) {
            return SessionUtils.getCartInSession(session);
        }
        CartDTO2 newCart = new CartDTO2();
        SessionUtils.setCartInSession(session, newCart);
        return newCart;
    }

    // 상품 담기
    @PostMapping
    public CartDTO2 add(HttpSession session, @RequestBody CartItemPostDTO cartItemPostDTO) {
        CartDTO2 cartDTO = cartService.update(SessionUtils.getCartInSession(session), cartItemPostDTO);
        SessionUtils.setCartInSession(session, cartDTO);
        return cartDTO;
    }
}

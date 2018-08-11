package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.User;
import codesquad.dto.AddCartProductDTO;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.support.ApiSuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@Slf4j
@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{productId}/1")
    public ResponseEntity<ApiSuccessResponse> addToCart(@PathVariable Long productId, @RequestBody CartProductDTO cartProductDTO, HttpSession session){
        log.debug("session check {} ", session);
        log.debug("carDTO {}", cartProductDTO);
        Cart cart = SessionUtils.getCartFromSession(session);
        User user = SessionUtils.getUserFromSession(session);
        Cart addedCart = cartService.addToCart(cartProductDTO, cart, user);
        return ResponseEntity.ok(new ApiSuccessResponse(HttpStatus.OK, addedCart, null));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ApiSuccessResponse> addToCart(@PathVariable Long productId, @RequestBody AddCartProductDTO cartProductDTO, HttpSession session) {
        Cart cart = SessionUtils.getCartFromSession(session);
        User user = SessionUtils.getUserFromSession(session);
        if(cart.isEmptyCart())
            cart.setUser(user);
        Cart addedCart = cartService.addToCart(cartProductDTO, cart, user);
        return ResponseEntity.ok(new ApiSuccessResponse(HttpStatus.OK, addedCart, null));
    }

}

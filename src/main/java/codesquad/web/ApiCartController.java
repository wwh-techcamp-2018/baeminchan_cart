package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.CartProduct;
import codesquad.domain.User;
import codesquad.dto.AddCartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import codesquad.support.ApiSuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Autowired
    private CartService cartService;
/*
    @PostMapping("/{productId}")
    public ResponseEntity<ApiSuccessResponse> addToCart(@PathVariable Long productId, @RequestParam CartProductDTO cartProductDTO, HttpSession session){
        log.debug("session check {} ", session);
        Cart cart = SessionUtils.getCartFromSession(session);
        User user = SessionUtils.getUserFromSession(session);
        Cart addedCart = cartService.addToCart(cartProductDTO, cart, user);
        return ResponseEntity.ok(new ApiSuccessResponse(HttpStatus.OK, addedCart, null));
    }
*/
    @PostMapping("/{productId}")
    public ResponseEntity<ApiSuccessResponse> addToCart(@PathVariable Long productId, @RequestBody AddCartProductDTO cartProductDTO, HttpSession session) {
        Cart cart = SessionUtils.getCartFromSession(session);
        User user = SessionUtils.getUserFromSession(session);
        Cart addedCart = cartService.addToCart(cartProductDTO, cart, user);
        return ResponseEntity.ok(new ApiSuccessResponse(HttpStatus.OK, addedCart, null));
    }

}

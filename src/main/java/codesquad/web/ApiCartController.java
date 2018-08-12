package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.User;
import codesquad.dto.CartProductDTO;
import codesquad.dto.SetCartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartProductService;
import codesquad.service.ProductService;
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
    private CartProductService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<ApiSuccessResponse> addToCart(@RequestBody CartProductDTO cartProductDTO, HttpSession session){
        Cart cart = SessionUtils.getCartFromSession(session);
        User user = SessionUtils.getUserFromSession(session);
        log.debug("cart in session {} {}", cart, cart.hashCode());
        log.debug("user in session {}", user);
        //cartProductDTO = productService.fillProduct(cartProductDTO);
        Cart addedCart = cartService.addToCart(cartProductDTO, cart, user);
        SessionUtils.setCartInSession(session, addedCart);
        log.debug("after addToCart cart in session {} {}", addedCart, cart.hashCode());
        return ResponseEntity.ok(new ApiSuccessResponse(HttpStatus.OK, addedCart, null));
    }
    @PutMapping("")
    public ResponseEntity<ApiSuccessResponse> changeAmount(@RequestBody SetCartProductDTO setCartProductDTO, HttpSession session){
        Cart cart = SessionUtils.getCartFromSession(session);
        User user = SessionUtils.getUserFromSession(session);
        Cart addedCart = cartService.changeCartItem(setCartProductDTO, cart, user);
        return ResponseEntity.ok(new ApiSuccessResponse(HttpStatus.OK, addedCart, null));
    }
/*
    @PostMapping("/{productId}")
    public ResponseEntity<ApiSuccessResponse> addToCart(@PathVariable Long productId, @RequestBody SetCartProductDTO cartProductDTO, HttpSession session) {
        Cart cart = SessionUtils.getCartFromSession(session);
        User user = SessionUtils.getUserFromSession(session);
        if(cart.isEmptyCart())
            cart.setUser(user);
        Cart addedCart = cartService.addToCart(cartProductDTO, cart, user);
        return ResponseEntity.ok(new ApiSuccessResponse(HttpStatus.OK, addedCart, null));
    }
*/
}

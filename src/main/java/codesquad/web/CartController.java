package codesquad.web;

import codesquad.domain.Cart;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Resource
    private CartService cartService;

    @GetMapping("/size")
    public ResponseEntity getCartNum(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(SessionUtils.CART_SESSION_KEY);
        if (!SessionUtils.hasCart(session)) {
            return ResponseEntity.ok().body(0);
        }
        return ResponseEntity.ok().body(cart.getProductSets().size());
    }

    @PostMapping("/{productId}")
    public ResponseEntity putInCart(@PathVariable long productId, HttpSession session) {
        Cart cart = new Cart();
        if (!SessionUtils.hasCart(session)) {
            session.setAttribute(SessionUtils.CART_SESSION_KEY, cart);
        }
        cart = (Cart) session.getAttribute(SessionUtils.CART_SESSION_KEY);
        cartService.putInCart(cart, productId);
        return ResponseEntity.ok().body(cart.getProductSets().size());
    }
}

package codesquad.web;

import codesquad.domain.Cart;
import codesquad.dto.CartItemDto;
import codesquad.security.SessionUtils;
import codesquad.service.CartItemService;
import codesquad.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Resource(name = "cartService")
    private CartService cartService;

    @Resource(name = "cartItemService")
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<Cart> cart(HttpSession httpSession) {
        Cart cart = getCart(httpSession);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/item")
    public ResponseEntity<Void> add(@RequestBody CartItemDto cartItemDto, HttpSession httpSession) {
        Cart cart = getCart(httpSession);
        cartItemService.save(cart, cartItemDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/item/count")
    public ResponseEntity<Integer> itemCount(HttpSession httpSession) {
        Cart cart = getCart(httpSession);
        return ResponseEntity.ok(cart.itemCount());
    }

    private Cart getCart(HttpSession httpSession) {
        // case : session.cart not exists.
        if (!SessionUtils.hasCart(httpSession))
            return createCart(httpSession);

        // case : session.cart exists but db.cart not exists.
        Long cartId = SessionUtils.getCartInSession(httpSession);
        Optional<Cart> maybeCart = cartService.findById(cartId);

        if(!maybeCart.isPresent()) {
            return createCart(httpSession);
        }

        // case : session.cart, db.cart exists.
        return maybeCart.get();
    }

    private Cart createCart(HttpSession httpSession) {
        Cart cart = cartService.save();
        SessionUtils.setCartInSession(httpSession, cart.getId());
        return cart;
    }
}

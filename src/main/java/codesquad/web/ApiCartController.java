package codesquad.web;

import codesquad.domain.*;
import codesquad.dto.CartItemDto;
import codesquad.security.SessionUtils;
import codesquad.service.CartItemService;
import codesquad.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Resource(name = "cartService")
    private CartService cartService;

    @Resource(name = "cartItemService")
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<Cart> cart(HttpSession httpSession) {
        Cart cart = get(httpSession);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/item")
    public ResponseEntity<Void> add(@RequestBody CartItemDto cartItemDto, HttpSession httpSession) {
        Cart cart = get(httpSession);
        CartItem item = cartItemService.save(cart, cartItemDto);
        cartService.save(cart.getId(), item);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<CartItem> item(@PathVariable Long id) {
        CartItem item = cartItemService.findById(id);
        return ResponseEntity.ok(item);
    }

    private Cart get(HttpSession httpSession) {
        if(!SessionUtils.hasCart(httpSession)) {
            Cart cart = cartService.save();
            SessionUtils.setCartInSession(httpSession, cart.getId());
            return cart;
        }
        Long cartId = SessionUtils.getCartInSession(httpSession);
        return cartService.findById(cartId);
    }
}

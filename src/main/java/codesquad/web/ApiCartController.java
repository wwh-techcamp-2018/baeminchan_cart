package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.CartItem;
import codesquad.domain.User;
import codesquad.dto.CartProductDto;
import codesquad.security.LoginUser;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/products")
    public ResponseEntity<Cart> saveProduct(@RequestBody CartProductDto dto, @LoginUser User user) {
        Cart savedCart = cartService.save(dto, user);
        return ResponseEntity
                .created(URI.create("/carts/products"))
                .body(savedCart);
    }

    @GetMapping("")
    public List<CartItem> getProducts(@LoginUser User user) {
        return cartService.getProductList(user);
    }
}

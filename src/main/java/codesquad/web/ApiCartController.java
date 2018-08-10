package codesquad.web;

import codesquad.domain.Cart;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cart addCart(@PathVariable Long id, @RequestBody int count) {
        return cartService.addCart(id, count);
    }
}

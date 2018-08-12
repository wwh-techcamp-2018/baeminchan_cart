package codesquad.web;

import codesquad.domain.Cart;
import codesquad.util.SessionCart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApiCartController {

    @GetMapping("/api/cart")
    public ResponseEntity<RestResponse> getCart(@SessionCart Cart cart) {
        return ResponseEntity.ok(new RestResponse(cart.getProducts()));
    }

    @PostMapping("/api/cart/products/{id}")
    public ResponseEntity<RestResponse> addProduct(@PathVariable Long id,
                                                   @RequestParam(value = "count", required = false) Integer count,
                                                   @SessionCart Cart cart) {
        cart.addProduct(id, Optional.ofNullable(count).orElse(1));
        return getCart(cart);
    }

    @PutMapping("/api/cart/products/{id}")
    public ResponseEntity<RestResponse> updateProduct(@PathVariable Long id,
                                                      @RequestParam(value = "count") Integer count,
                                                      @SessionCart Cart cart) {
        cart.updateProduct(id, count);
        return getCart(cart);
    }

    @DeleteMapping("/api/cart/products/{id}")
    public ResponseEntity<RestResponse> deleteProduct(@PathVariable Long id,
                                                      @RequestParam(value = "count", required = false) Integer count,
                                                      @SessionCart Cart cart) {
        cart.deleteProduct(id, count);
        return getCart(cart);
    }

}

package codesquad.web;

import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/api/cart")
    public ResponseEntity<RestResponse> getCart(HttpSession session) {
        return ResponseEntity.ok(new RestResponse(cartService.getProducts(session)));
    }

    @PostMapping("/api/cart/products/{id}")
    public ResponseEntity<RestResponse> addProduct(@PathVariable Long id,
                                                   @RequestParam(value = "count", required = false) Integer count,
                                                   HttpSession session) {
        cartService.addProduct(session, id, Optional.ofNullable(count).orElse(1));
        return getCart(session);
    }

    @PutMapping("/api/cart/products/{id}")
    public ResponseEntity<RestResponse> updateProduct(@PathVariable Long id,
                                                      @RequestParam(value = "count") Integer count,
                                                      HttpSession session) {
        cartService.updateProduct(session, id, count);
        return getCart(session);
    }

    @DeleteMapping("/api/cart/products/{id}")
    public ResponseEntity<RestResponse> deleteProduct(@PathVariable Long id,
                                                      @RequestParam(value = "count", required = false) Integer count,
                                                      HttpSession session) {
        cartService.deleteProduct(session, id, count);
        return getCart(session);
    }

}

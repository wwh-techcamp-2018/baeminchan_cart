package codesquad.web;

import codesquad.domain.Cart;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("")
    public List<Cart> readCarts() {
        return cartService.readCarts();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Cart update(@RequestBody Cart cart) {
        return cartService.update(cart);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        cartService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cart read(@PathVariable Long id) {
        return cartService.read(id);
    }
}

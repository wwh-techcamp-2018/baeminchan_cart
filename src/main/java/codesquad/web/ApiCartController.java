package codesquad.web;

import codesquad.annotation.WithCart;
import codesquad.domain.Cart;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public Cart get(@WithCart Cart cart) {
        return cart;
    }

    @PostMapping("/{pid}")
    public Cart add(@PathVariable Long pid, @WithCart Cart cart) {
        cartService.add(cart, pid);
        return cart;
    }

    @PutMapping("/{pid}/sub")
    public String sub(@PathVariable Long pid) {
        return "";
    }

    @DeleteMapping("/{pid}")
    public String delete(@PathVariable Long pid) {
        return "";
    }
}

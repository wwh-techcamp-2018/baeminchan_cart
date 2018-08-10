package codesquad.web;

import codesquad.domain.CartItem;
import codesquad.domain.User;
import codesquad.dto.CartProductDto;
import codesquad.security.LoginUser;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/products")
    public void saveProduct(@RequestBody CartProductDto dto, @LoginUser User user) {
        cartService.save(dto, user);
    }

    @GetMapping("")
    public List<CartItem> getProducts(@LoginUser User user) {
        return cartService.getProductList(user);
    }
}

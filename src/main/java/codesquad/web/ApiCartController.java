package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.dto.CartDto;
import codesquad.security.SessionUtils;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {
    @Autowired
    private ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addCart(@RequestBody CartDto cartDto, HttpSession session) {
        Product product = productService.findById(cartDto.getProductId());
        Cart cart = SessionUtils.getCarts(session).addItem(product, cartDto.getCount());
        SessionUtils.saveCart(session, cart);

        Map<String, Object> response = new HashMap<>();
        response.put("productTitle", product.getTitle());
        response.put("totalKind", cart.getItemSize());
        return response;
    }

    @GetMapping("/size")
    public Map<String, Object> getCartSize(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalKind", SessionUtils.getCarts(session).getItemSize());
        return response;
    }
}

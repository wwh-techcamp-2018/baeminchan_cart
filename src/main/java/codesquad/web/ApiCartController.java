package codesquad.web;


import codesquad.domain.Cart;
import codesquad.domain.CartItem;
import codesquad.domain.Product;
import codesquad.dto.CartItemDTO;
import codesquad.service.ProductService;
import codesquad.utils.SessionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/products/cart")
public class ApiCartController {

    @Resource(name = "productService")
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Void> saveToCart(@RequestBody CartItemDTO cartItemDTO, HttpSession httpSession){
        Product product = productService.findById(cartItemDTO.getProductId());
        CartItem cartItem = new CartItem(product, cartItemDTO.getQuantity());
        Cart cart = SessionUtils.getCartFromSession(httpSession);
        cart.addCartItem(cartItem);
        httpSession.setAttribute(SessionUtils.CART_SESSION_KEY, cart);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/count")
//    private ResponseEntity<Integer> countItemList(){
//        return ResponseEntity.ok(cartRepository.findAll().size());
//    }
}

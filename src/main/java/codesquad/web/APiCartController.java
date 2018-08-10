package codesquad.web;

//import codesquad.domain.Cart;
//import codesquad.dto.CartDTO;
//import codesquad.service.CartService;
import codesquad.dto.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("/cart")
public class APiCartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @PostMapping("/hold")
//    public ResponseEntity<Cart> holdProduct(@RequestBody CartDTO cartDto) {
//        return ResponseEntity.ok(cartService.holdProduct(cartDto));
//    }

    @PostMapping("/hold")
    public ResponseEntity<Void> holdProduct(@RequestBody CartItem cartItem, HttpSession httpSession) {
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>)httpSession.getAttribute("cartItems");

        if (cartItems == null) cartItems = new ArrayList<CartItem>();

        cartItems.add(cartItem);
        httpSession.setAttribute("cartItems",cartItems);

        return ResponseEntity.ok().build();
    }
}

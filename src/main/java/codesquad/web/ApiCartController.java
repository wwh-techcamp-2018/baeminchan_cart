package codesquad.web;


import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.security.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/products/cart")
public class ApiCartController {

    @Autowired
    private CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<Void> saveToCart(@RequestBody Cart cart, HttpSession httpSession){
//        httpSession.setAttribute(SessionUtils.CART_SESSION_KEY, );
        cartRepository.save(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    private ResponseEntity<Integer> countItemList(){
        int counter = cartRepository.findAll().size();
        return ResponseEntity.ok(counter);
    }
}

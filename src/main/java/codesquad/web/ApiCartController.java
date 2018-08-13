package codesquad.web;


import codesquad.domain.Product;
import codesquad.security.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/products/cart")
public class ApiCartController {


    @PostMapping
    public ResponseEntity<Void> saveToCart(@RequestBody Product product, HttpSession httpSession){
        httpSession.setAttribute(SessionUtils.CART_SESSION_KEY, product);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/count")
//    private ResponseEntity<Integer> countItemList(){
//        //return ResponseEntity.ok(cartRepository.findAll().size());
//    }
}

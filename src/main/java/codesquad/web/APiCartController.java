package codesquad.web;

import codesquad.dto.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("/cart")
public class APiCartController {

    @PostMapping("/hold")
    public ResponseEntity<Void> holdProduct(@RequestBody CartItem cartItem, HttpSession httpSession) {
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>)httpSession.getAttribute("cartItems");

        if (cartItems == null) cartItems = new ArrayList<>();

        cartItems.add(cartItem);
        httpSession.setAttribute("cartItems",cartItems);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId, HttpSession httpSession) {
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>)httpSession.getAttribute("cartItems");
        cartItems.removeIf(cartItem -> cartItem.getProductId().equals(productId));
        httpSession.setAttribute("cartItems", cartItems);

        return ResponseEntity.ok().build();
    }
}

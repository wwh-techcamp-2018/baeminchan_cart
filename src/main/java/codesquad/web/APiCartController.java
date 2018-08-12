package codesquad.web;

import codesquad.dto.CartItem;
import codesquad.dto.CartItemList;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/cart")
public class APiCartController {

    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody CartItem cartItem, HttpSession httpSession) {
        CartItemList cartItemList = Optional
                .ofNullable((CartItemList)httpSession.getAttribute("cartItemList"))
                .orElse(new CartItemList());
        cartItemList.update(cartItem);
        httpSession.setAttribute("cartItemList", cartItemList);

        return ResponseEntity.ok().build();
    }
//
//    @DeleteMapping("/{productId}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId, HttpSession httpSession) {
//        ArrayList<CartItem> cartItems = (ArrayList<CartItem>)httpSession.getAttribute("cartItems");
//        cartItems.removeIf(cartItem -> cartItem.getProductId().equals(productId));
//        httpSession.setAttribute("cartItems", cartItems);
//
//        return ResponseEntity.ok().build();
//    }
}

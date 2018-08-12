package codesquad.web;

import codesquad.dto.CartItem;
import codesquad.dto.CartItemList;
import codesquad.util.RestResponse;
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
    public ResponseEntity<RestResponse> updateProduct(@RequestBody CartItem cartItem, HttpSession httpSession) {
        CartItemList cartItemList = Optional
                .ofNullable((CartItemList)httpSession.getAttribute("cartItemList"))
                .orElse(new CartItemList());
        cartItemList.update(cartItem);
        httpSession.setAttribute("cartItemList", cartItemList);

        log.debug("cartItemList : {}", cartItemList);
        log.debug("cartItemList2 : {}", RestResponse.of(cartItemList.getCartItemCount()));
        return ResponseEntity.ok().body(RestResponse.of(cartItemList.getCartItemCount()));
    }
}

package codesquad.web;

import codesquad.domain.CartItem;
import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.dto.CartInfoDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartItemService;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class ApiCartController {

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    // TODO: uid는 세션에서
    @GetMapping("")
    public ResponseEntity<List<CartItem>> list(HttpSession session) {
        return new ResponseEntity<List<CartItem>>(cartItemService.findByUserId(SessionUtils.getUser(session).getId()), HttpStatus.OK);
    }

    @PostMapping("/{pid}/{quantity}")
    public ResponseEntity<CartInfoDTO> createCartItem(HttpSession session, @PathVariable Long pid, @PathVariable Long quantity){
        Product product = productService.findById(pid);
        User loginUser = SessionUtils.getUser(session);

        CartItem cartItem = cartItemService.findByUserIdAndProductId(loginUser.getId(), pid);

        if(cartItem == null)
            cartItem = new CartItem(product,loginUser);

        cartItem.addQuantity(quantity);

        cartItemService.save(cartItem);

        CartInfoDTO cartInfoDTO = CartInfoDTO.of(product, cartItemService.findByUserId(loginUser.getId()));

        return new ResponseEntity<CartInfoDTO>(cartInfoDTO, HttpStatus.OK);
    }
}

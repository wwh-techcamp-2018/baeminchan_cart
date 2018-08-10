package codesquad.web;

import codesquad.domain.CartItem;
import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.dto.CartInfoDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartItemService;
import codesquad.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class ApiCartController {

    private static final Logger log = LoggerFactory.getLogger(ApiCartController.class);

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @GetMapping("")
    public ResponseEntity<List<CartItem>> list(HttpSession session) {
        // TODO: HttpStatus바꾸기 -> fetchManager바꿔야함
        return new ResponseEntity<List<CartItem>>(cartItemService.findByUserId(SessionUtils.getUser(session).getId()), HttpStatus.CREATED);
    }

    @GetMapping("/init")
    public ResponseEntity<CartInfoDTO> init(HttpSession session){
        if(!SessionUtils.isLoginUser(session))
            return new ResponseEntity<CartInfoDTO>(new CartInfoDTO(0), HttpStatus.CREATED);
        CartInfoDTO cartInfoDTO = new CartInfoDTO(cartItemService.findByUserId(SessionUtils.getUser(session).getId()).size());

        return new ResponseEntity<CartInfoDTO>(cartInfoDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{pid}/{quantity}")
    public ResponseEntity<CartInfoDTO> createCartItem(HttpSession session, @PathVariable Long pid, @PathVariable Long quantity){
        Product product = productService.findById(pid);
        User loginUser = SessionUtils.getUser(session);

        log.debug("login user : {}", loginUser);

        CartItem cartItem = cartItemService.findByUserIdAndProductId(loginUser.getId(), pid);

        if(cartItem == null)
            cartItem = new CartItem(product,loginUser);

        cartItem.addQuantity(quantity);

        cartItemService.save(cartItem);

        CartInfoDTO cartInfoDTO = CartInfoDTO.of(product, cartItemService.findByUserId(loginUser.getId()));

        return new ResponseEntity<CartInfoDTO>(cartInfoDTO, HttpStatus.CREATED);
    }
}

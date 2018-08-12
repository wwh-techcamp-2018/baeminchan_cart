package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.CartProduct;
import codesquad.domain.Product;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/carts")
public class ApiCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CartProduct> addCartProduct(@RequestBody CartProductDTO cartProductDTO, HttpSession session) {
        if(!SessionUtils.isCartUser(session)) {
            SessionUtils.setCartUserInSession(session);
        }
        log.debug("cartProductDTO : {}", cartProductDTO);
        Cart cart = SessionUtils.getCartUserInSession(session);
        Product product = productService.findById(cartProductDTO.getProductId());

        CartProduct cartProduct = cartProductDTO.toCartProduct(product);
        cart.addCartProduct(cartProduct);
        log.debug("cart : {}", cart);

        return ResponseEntity.status(HttpStatus.OK).body(cartProduct);
    }
}

package codesquad.web.api;

import codesquad.domain.Product;
import codesquad.dto.CartProductDTO;
import codesquad.dto.ProductDTO;
import codesquad.exception.UnAuthenticationException;
import codesquad.service.ProductService;
import codesquad.utils.CartUtils;
import codesquad.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/carts")
public class ApiCartController {
    @Autowired
    private ProductService productService;
    private List<CartProductDTO> cartItems = new ArrayList<>();


    @ResponseBody
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO, HttpSession session) {
        log.debug("productDTO : {}", productDTO);
        if(!CartUtils.isEmptyCart(session)) {
            cartItems = (List<CartProductDTO>) session.getAttribute(CartUtils.CART_SESSION_KEY);
        }

        Product savedProduct = productService.getProduct(productDTO.getProductId());
        cartItems.add(new CartProductDTO(savedProduct, productDTO.getCount()));
        session.setAttribute(CartUtils.CART_SESSION_KEY, cartItems);

        if(SessionUtils.isLoginUser(session)) {
            productService.saveCartItem(cartItems, SessionUtils.getUserFromSession(session));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId, HttpSession session) throws UnAuthenticationException {
        Product savedProduct = productService.getProduct(productId);
        if(SessionUtils.isLoginUser(session)) {
            productService.deleteCartItem(savedProduct, SessionUtils.getUserFromSession(session));
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        cartItems = (List<CartProductDTO>) session.getAttribute(CartUtils.CART_SESSION_KEY);
        cartItems.remove(new CartProductDTO(savedProduct));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

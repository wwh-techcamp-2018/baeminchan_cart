package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.domain.ResponseModel;
import codesquad.dto.CartPriceDTO;
import codesquad.dto.CartProductDTO;
import codesquad.security.CartSession;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/cart")
public class ApiCartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products/count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<Integer> countProducts(@CartSession Cart cart) {
        return ResponseModel.ofSuccess(cart.getSumProductNum());
    }

    @PostMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<Integer> addProduct(@CartSession Cart cart, @PathVariable Long productId, @RequestParam("add") int productNum, HttpSession session) {
        Cart updatedCart = cartService.addProduct(cart, productId, productNum);
        SessionUtils.setCartInSession(session, updatedCart);

        return ResponseModel.ofSuccess(updatedCart.productNum(productId));
    }

    @PutMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<Integer> updateProduct(@CartSession Cart cart, @PathVariable Long productId, @RequestParam("num") int productNum, HttpSession session) {
        Cart updatedCart = cartService.updateProduct(cart, productId, productNum);
        SessionUtils.setCartInSession(session, updatedCart);

        return ResponseModel.ofSuccess(updatedCart.productNum(productId));

    }

    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<CartProductDTO> getProductDetails(@CartSession Cart cart, @PathVariable Long productId) {
        Product product = productService.findById(productId);
        return ResponseModel.ofSuccess(CartProductDTO.from(cart, product));
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<List<CartProductDTO>> getProductListDetails(@CartSession Cart cart) {
        List<Product> products = cartService.getProducts(cart);
        return ResponseModel.ofSuccess(CartProductDTO.listFrom(cart, products));
    }

    @GetMapping("/price")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<CartPriceDTO> totalPrice(@CartSession Cart cart) {
        return ResponseModel.ofSuccess(CartPriceDTO.from(cart, cartService.getProducts(cart)));
    }
}

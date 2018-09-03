package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.domain.ResponseModel;
import codesquad.dto.CartPriceDTO;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
@Slf4j
public class ApiCartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<Integer> addProductToCart(@PathVariable Long productId, @RequestParam("num") int productNum, HttpSession session, NativeWebRequest webRequest) {
        // Todo: Extract checking session to prehandle
        setCartInSessionIfNotExist(session);
        Cart cart = SessionUtils.getCartFromSession(webRequest).get();
        setCartUserIfLogin(session, cart, webRequest);

        Product product = productService.findById(productId);

        Cart updatedCart = cartService.updateProductInCart(cart, product, cart.productNum(productId) + productNum);
        SessionUtils.setCartInSession(session, updatedCart);

        return ResponseModel.ofSuccess(updatedCart.getSumProductNum());
    }

    @PostMapping("/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<CartProductDTO> updateProduct(@PathVariable Long productId, @RequestParam("num") int productNum, HttpSession session, NativeWebRequest webRequest) {
        Cart cart = SessionUtils.getCartFromSession(webRequest).get();
        setCartUserIfLogin(session, cart, webRequest);

        Product product = productService.findById(productId);

        Cart updatedCart = cartService.updateProductInCart(cart, product, productNum);
        SessionUtils.setCartInSession(session, updatedCart);

        return ResponseModel.ofSuccess(CartProductDTO.from(updatedCart, product));
    }

    @GetMapping("/price")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<CartPriceDTO> totalPrice(HttpSession session, NativeWebRequest webRequest) {
        setCartInSessionIfNotExist(session);
        Cart cart = SessionUtils.getCartFromSession(webRequest).get();
        setCartUserIfLogin(session, cart, webRequest);

        return ResponseModel.ofSuccess(CartPriceDTO.from(cart, cartService.getProducts(cart)));
    }

    private void setCartInSessionIfNotExist(HttpSession session) {
        if (!SessionUtils.isCart(session)) {
            SessionUtils.setCartInSession(session, cartService.create(null));
        }
    }

    private void setCartUserIfLogin(HttpSession session, Cart cart, NativeWebRequest webRequest) {
        if (SessionUtils.isLoginUser(session)) {
            cart.setUserIfNot(SessionUtils.getUserFromSession(webRequest).get());
        }
    }
}

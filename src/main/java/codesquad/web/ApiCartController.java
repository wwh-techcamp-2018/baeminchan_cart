package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.ResponseModel;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
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

    @PostMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<Integer> addProduct(@PathVariable Long productId, HttpSession session, NativeWebRequest webRequest) {
        setCartInSessionIfNotExist(session);
        Long cartId = SessionUtils.getCartIdFromSession(webRequest);

        setCartUserIfLogin(session, webRequest, cartId);

        return ResponseModel.ofSuccess(cartService.add(cartId, productId).getSumProductNum());
    }

    private void setCartInSessionIfNotExist(HttpSession session) {
        if (!SessionUtils.isCart(session)) {
            SessionUtils.setCartInSession(session, cartService.create().getId());
        }
    }

    private void setCartUserIfLogin(HttpSession session, NativeWebRequest webRequest, Long cartId) {
        if (SessionUtils.isLoginUser(session)) {
            Cart cart = cartService.findById(cartId);
            cart.setUserIfNot(SessionUtils.getUserFromSession(webRequest));
        }
    }
}

package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.ResponseModel;
import codesquad.domain.User;
import codesquad.dto.CartDto;
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

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<Integer> addProduct(@RequestBody CartDto dto, HttpSession session, NativeWebRequest webRequest) {
        setCartSessionIfNotExist(session);
        Cart cart = SessionUtils.getCartFromSession(webRequest);

        if (SessionUtils.isLoginUser(session)) {
            setCartUser(cart, webRequest);
        }

        Cart updatedCart = cartService.add(cart, dto);
        SessionUtils.setCartInSession(session, updatedCart);

        return ResponseModel.ofSuccess(updatedCart.getSumProductNum());
    }

    private void setCartSessionIfNotExist(HttpSession session) {
        if (!SessionUtils.isCart(session)) {
            SessionUtils.setCartInSession(session, cartService.create());
        }
    }

    private void setCartUser(Cart cart, NativeWebRequest webRequest) {
        User user = SessionUtils.getUserFromSession(webRequest);
        if (!cart.matchUser(user)) {
            cart.setUser(user);
        }
    }
}

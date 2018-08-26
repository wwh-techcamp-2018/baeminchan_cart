package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.dto.CartPriceDto;
import codesquad.dto.CartProductDto;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String show(HttpSession session, Model model, NativeWebRequest webRequest) {
        setCartInSessionIfNotExist(session);
        Cart cart = SessionUtils.getCartFromSession(webRequest).get();
        setCartUserIfLogin(session, cart, webRequest);

        List<Product> products = cartService.getProducts(cart);
        model.addAttribute("cartProducts", CartProductDto.listFrom(cart, products));

        model.addAttribute("cartPrice", CartPriceDto.from(cart));

        return "cart";
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

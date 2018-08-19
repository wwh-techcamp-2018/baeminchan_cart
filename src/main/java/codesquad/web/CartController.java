package codesquad.web;

import codesquad.dto.CartProductDto;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String show(Model model, NativeWebRequest webRequest) {
        List<CartProductDto> cartProducts = cartService.getCartProducts(cartService.findById(SessionUtils.getCartIdFromSession(webRequest)));
        Long productTotalPrice = cartService.computeCartTotalPrice(cartProducts);
        Long deliveryPrice = cartService.getDeliveryPrice(productTotalPrice);

        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("totalProductPrice", productTotalPrice);
        model.addAttribute("deliveryPrice", deliveryPrice);
        model.addAttribute("totalCartPrice", productTotalPrice + deliveryPrice);

        return "cart";
    }
}

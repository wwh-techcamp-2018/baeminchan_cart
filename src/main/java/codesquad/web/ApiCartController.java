package codesquad.web;

import codesquad.common.RestResponse;
import codesquad.domain.CartItem;
import codesquad.domain.CartItemLog;
import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.dto.CartItemDTO;
import codesquad.security.LoginUser;
import codesquad.security.SessionUtils;
import codesquad.service.CartLogService;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/carts")
public class ApiCartController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public RestResponse<CartItem> addCartItem(@LoginUser(required=false) User user,
                                                 @RequestBody CartItemDTO dto,
                                                 HttpSession session) {
        Product product = productService.findById(dto.getProductId());
        CartItem cartItem = new CartItem(product, dto.getCount());
        SessionUtils.addCartItemInSession(session, cartItem);
        return RestResponse.success(cartItem);
    }
}

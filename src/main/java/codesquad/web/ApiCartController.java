package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.dto.CartDTO;
import codesquad.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    private static final Logger log = LoggerFactory.getLogger(ApiCartController.class);
    public List<Cart> myCart = new ArrayList<>();


    @Autowired
    ProductService productService;

    @PostMapping("")
    public Long addToCart(HttpSession httpSession, @RequestBody CartDTO cartDTO){
        log.debug("cartDTO : {}", cartDTO);
        Cart cart = productService.convertToCart(cartDTO);
        log.debug("cart : {}", cart);
        myCart.add(cart);
        return new Long(myCart.size());
    }

}

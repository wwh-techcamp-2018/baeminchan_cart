package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Order;
import codesquad.domain.Product;
import codesquad.dto.CartDTO;
import codesquad.security.SessionUtils;
import codesquad.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class ApiCartController {

    private static final Logger log = LoggerFactory.getLogger(ApiCartController.class);

    @Autowired
    ProductService productService;

    @PostMapping("")
    public Order addCart(HttpSession session, @RequestBody CartDTO cartDTO){
        Product product = productService.findById(cartDTO.getProductId());
        Order order = SessionUtils.addProductInSessionCart(session, product, cartDTO.getAmount());
        return order;
    }
}

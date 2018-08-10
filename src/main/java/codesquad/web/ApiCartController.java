package codesquad.web;

import codesquad.domain.CartDTO;
import codesquad.domain.Product;
import codesquad.security.SessionUtils;
import codesquad.service.ProductService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ApiCartController {
    
    private static final Logger log = LoggerFactory.getLogger(ApiCartController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/addProducts/{productId}/{count}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> addProducts(HttpSession session, @PathVariable Long productId, @PathVariable Long count) {
        if(productId != -1) {
            Product addableProduct = productService.findById(productId);
            return SessionUtils.getCartInSession(session).addProducts(addableProduct, count).getProducts();
        } else {
            return SessionUtils.getCartInSession(session).getProducts();
        }
    }

    @GetMapping("/cartInfo")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO getCartInfo(HttpSession session) {
        return new CartDTO(SessionUtils.getCartInSession(session));
    }

    @GetMapping("/setProducts/{productId}/{count}")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO setProducts(HttpSession session, @PathVariable Long productId, @PathVariable Long count) {
        Product setableProduct = productService.findById(productId);
        return new CartDTO(SessionUtils.getCartInSession(session).setProducts(setableProduct, count));
    }
}

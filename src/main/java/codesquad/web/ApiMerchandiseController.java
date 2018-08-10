package codesquad.web;

import codesquad.domain.Merchandise;
import codesquad.domain.ProductRepository;
import codesquad.domain.ShoppingBasket;
import codesquad.exception.RestResponse;
import codesquad.security.BasicAuthInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/merchandise")
public class ApiMerchandiseController {

    private static final Logger log = LoggerFactory.getLogger(ApiMerchandiseController.class);

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("")
    public ResponseEntity<RestResponse> create(@RequestBody Merchandise mer, HttpSession httpSession) {

        log.debug("pId : {}", mer.getpId());

        Merchandise merchandise = new Merchandise(mer.getAmount(),false, productRepository.findById(mer.getpId()).get());

        if(httpSession.getAttribute("SHOPPING_CART") == null) {
            httpSession.setAttribute("SHOPPING_CART", new ShoppingBasket());
        }

        ShoppingBasket shoppingBasket = (ShoppingBasket) httpSession.getAttribute("SHOPPING_CART");
        shoppingBasket.put(merchandise);

        return ResponseEntity.ok(new RestResponse(shoppingBasket.getMerchandises().keySet().size()));
    }

}

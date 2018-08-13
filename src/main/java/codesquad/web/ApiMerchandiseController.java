package codesquad.web;

import codesquad.domain.Merchandise;
import codesquad.domain.ProductRepository;
import codesquad.domain.ShoppingBasket;
import codesquad.dto.CartDTO;
import codesquad.exception.RestResponse;
import codesquad.security.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/merchandise")
public class ApiMerchandiseController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("")
    public ResponseEntity<RestResponse> addCart(@RequestBody CartDTO cartDTO, HttpSession httpSession) {
        log.debug("cartDTO : {} {}", cartDTO.getId(), cartDTO.getAmount());

        ShoppingBasket shoppingBasket = SessionUtils.getShoppingCartInSession(httpSession);
        shoppingBasket.putCart(cartDTO);

        return ResponseEntity.ok(new RestResponse(shoppingBasket.getCartSize()));
    }

}

package codesquad.web;

import codesquad.domain.ProductBundle;
import codesquad.dto.ProductBundleDto;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/cart/products/{product_id}")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ProductBundle saveProductBundle(
            HttpSession session,
            @RequestBody @Valid ProductBundleDto dto,
            @PathVariable(value = "product_id") Long id
    ) {
        return cartService.saveProductBundle(SessionUtils.getUserInSession(session), dto, id);
    }

    @DeleteMapping("")
    public ProductBundle removeProductBundle(
            HttpSession session,
            @PathVariable(value = "product_id") long id
    ) {
        return cartService.removeProductBundle(SessionUtils.getUserInSession(session), id);
    }
}

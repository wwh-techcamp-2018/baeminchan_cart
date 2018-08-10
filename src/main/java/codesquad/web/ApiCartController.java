package codesquad.web;

import codesquad.domain.ProductBundle;
import codesquad.dto.ProductBundleInputDto;
import codesquad.dto.ProductBundleOutputDto;
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
    public ProductBundleOutputDto saveProductBundle(
            HttpSession session,
            @RequestBody @Valid ProductBundleInputDto dto,
            @PathVariable(value = "product_id") Long id
    ) {
        return cartService.saveProductBundle(SessionUtils.getUserInSession(session), dto, id);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ProductBundleOutputDto resetProductBundle(
            HttpSession session,
            @RequestBody @Valid ProductBundleInputDto dto,
            @PathVariable(value = "product_id") Long id
    ) {
        return cartService.resetProductBundle(SessionUtils.getUserInSession(session), dto, id);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ProductBundleOutputDto removeProductBundle(
            HttpSession session,
            @PathVariable(value = "product_id") long id
    ) {
        return cartService.removeProductBundle(SessionUtils.getUserInSession(session), id);
    }
}

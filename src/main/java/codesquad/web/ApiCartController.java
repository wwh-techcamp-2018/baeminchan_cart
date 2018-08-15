package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.dto.CartDTO;
import codesquad.dto.CartProductDTO;
import codesquad.service.ProductService;
import codesquad.util.SessionCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getCartList(@SessionCart Cart cart) {
        List<CartProductDTO> cartProducts = productService.findAll(cart.getProductIds()).stream()
                .map(product -> new CartProductDTO(product, cart.getProductCount(product.getId())))
                .collect(Collectors.toList());
        return new RestResponse(new CartDTO(cartProducts));
    }

    @GetMapping("/products/count")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getCartCount(@SessionCart Cart cart) {
        return new RestResponse(cart.getProducts().keySet().size());
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getProductDetail(@PathVariable Long id, @SessionCart Cart cart) {
        Product product = productService.findById(id);
        Integer count = cart.getProductCount(id);
        return new RestResponse(new CartProductDTO(product, count));
    }

    @PostMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse addProduct(@PathVariable Long id,
                                   @RequestParam(value = "count", required = false) Integer count,
                                   @SessionCart Cart cart) {
        cart.addProduct(id, count);
        return getCartCount(cart);
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updateProduct(@PathVariable Long id,
                                      @RequestParam(value = "count") Integer count,
                                      @SessionCart Cart cart) {
        cart.updateProduct(id, count);
        return getCartCount(cart);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id, @SessionCart Cart cart) {
        cart.deleteProduct(id);
    }

}

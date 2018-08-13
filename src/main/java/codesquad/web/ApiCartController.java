package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.dto.CartProductDTO;
import codesquad.service.ProductService;
import codesquad.util.SessionCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<RestResponse> getCartList(@SessionCart Cart cart) {
        List<CartProductDTO> cartProducts = productService.findAll(cart.getProductIds()).stream()
                .map(product -> new CartProductDTO(product, cart.getProductCount(product.getId())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new RestResponse(cartProducts));
    }

    @GetMapping("/count")
    public ResponseEntity<RestResponse> getCartCount(@SessionCart Cart cart) {
        return ResponseEntity.ok(new RestResponse(cart.getProducts().keySet().size()));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<RestResponse> getProductDetail(@PathVariable Long id, @SessionCart Cart cart) {
        Product product = productService.findById(id);
        Integer count = cart.getProductCount(id);
        return ResponseEntity.ok(new RestResponse(new CartProductDTO(product, count)));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id,
                                                      @RequestParam(value = "count") Integer count,
                                                      @SessionCart Cart cart) {
        cart.updateProduct(id, count);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @SessionCart Cart cart) {
        cart.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

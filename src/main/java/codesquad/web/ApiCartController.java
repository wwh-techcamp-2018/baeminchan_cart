package codesquad.web;

import codesquad.domain.Product;
import codesquad.dto.ProductDTO;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/")
public class ApiCartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PutMapping("{productId}")
    public ResponseEntity addProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        Product product = productService.findById(productId);
        cartService.addProduct(product, productDTO.getCount());
        return ResponseEntity.ok().build();
    }
}

package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.exception.ResourceNotFoundException;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public Cart showProducts(@PathVariable Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("장바구니가 존재하지 않습니다."));
        return cart;
    }

    @PostMapping("/{id}")
    @Transactional
    public Integer addProduct(@PathVariable Long id, @RequestBody Long productId) {
        Product product = productRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("반찬이 존재하지 않습니다."));
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("장바구나가 존재하지 않습니다."));
        cart.addProduct(product);
        return cart.getProductList().size();
    }
}

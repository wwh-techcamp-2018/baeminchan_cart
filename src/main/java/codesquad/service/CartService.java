package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    public Cart add(Cart cart, Long pid) {
        Product product = productRepository.findById(pid).orElseThrow(IllegalArgumentException::new);
        return cart.add(product);
    }
}

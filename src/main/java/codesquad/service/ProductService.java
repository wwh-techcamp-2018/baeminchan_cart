package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id).get();
    }

    public Cart convertToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.addToCart(findById(cartDTO.getId()), cartDTO.getQuantity(), cartDTO.getSaleRate());
        return cart;
    }
}

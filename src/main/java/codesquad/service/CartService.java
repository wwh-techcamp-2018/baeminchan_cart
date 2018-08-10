package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartProduct;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    private Cart localCart = new Cart();

    public int add(Long productId, int quantity) {
        return localCart.add(productId, quantity);
    }

    public List<CartProduct> getCartProducts() {
        return localCart.getCartProducts();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }
}

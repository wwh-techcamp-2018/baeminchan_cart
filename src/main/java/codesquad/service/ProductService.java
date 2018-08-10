package codesquad.service;

import codesquad.domain.CartProduct;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Product> getProductsByCartProducts(List<CartProduct> cartProducts) {
        // cartProduct로 Products가져와야함
        return cartProducts
                .stream()
                .map(cartProduct -> {
            return productRepository.findById(cartProduct.getProductId()).get();
        }).collect(Collectors.toList());
    }
}

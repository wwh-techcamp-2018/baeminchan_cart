package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.ProductBundleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductBundleRepository productBundleRepository;

    public ProductBundle saveProductBundle(User user, ProductBundleDto dto, long productId) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);

        Optional<ProductBundle> maybeProductBundle = productBundleRepository.findByUserAndProduct(user, product);
        if (maybeProductBundle.isPresent())
            return productBundleRepository.save(maybeProductBundle.get().update(dto));

        ProductBundle bundle = ProductBundle.builder()
                .user(user)
                .product(product)
                .count(dto.getCount())
                .build();

        return productBundleRepository.save(bundle);
    }

    public ProductBundle removeProductBundle(User user, long id) {
        ProductBundle productBundle = productBundleRepository.findById(id).orElseThrow(RuntimeException::new);
        productBundleRepository.delete(productBundle);
        return productBundle;
    }

}

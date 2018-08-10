package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.ProductBundleInputDto;
import codesquad.dto.ProductBundleOutputDto;
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

    public ProductBundleOutputDto saveProductBundle(User user, ProductBundleInputDto dto, long productId) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);

        Optional<ProductBundle> maybeProductBundle = productBundleRepository.findByUserAndProduct(user, product);
        if (maybeProductBundle.isPresent())
            return new ProductBundleOutputDto(
                    productBundleRepository.save(maybeProductBundle.get().update(dto)),
                    totalBundleSize(user)
            );

        ProductBundle bundle = ProductBundle.builder()
                .user(user)
                .product(product)
                .count(dto.getCount())
                .build();

        return new ProductBundleOutputDto(
                productBundleRepository.save(bundle),
                totalBundleSize(user)
        );
    }

    public ProductBundleOutputDto resetProductBundle(User user, ProductBundleInputDto dto, long productId) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        ProductBundle productBundle = productBundleRepository.findByUserAndProduct(user, product).orElseThrow(RuntimeException::new);
        return new ProductBundleOutputDto(
                productBundleRepository.save(productBundle.reset(dto)),
                totalBundleSize(user)
        );
    }

    public ProductBundleOutputDto removeProductBundle(User user, long id) {
        ProductBundle productBundle = productBundleRepository.findById(id).orElseThrow(RuntimeException::new);
        productBundleRepository.delete(productBundle);
        return new ProductBundleOutputDto(
                productBundle,
                totalBundleSize(user)
        );
    }

    private int totalBundleSize(User user) {
        return productBundleRepository.findAllByUser(user).size();
    }

}

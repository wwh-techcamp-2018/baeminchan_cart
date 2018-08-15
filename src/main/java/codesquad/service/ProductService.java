package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAll(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

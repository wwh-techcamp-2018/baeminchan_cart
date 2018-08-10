package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 반찬을 찾을 수 없습니다."));
    }
}

package codesquad.service;

import codesquad.common.Message;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSourceAccessor.getMessage(Message.NOT_EXIST_PRODUCT)));
    }
}

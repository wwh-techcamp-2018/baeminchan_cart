package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartProductDTO;
import codesquad.exception.ResourceNotFoundException;
import codesquad.support.PriceCalcultor;
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

    //todo NOTFOUNDExcpetion
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Product> findByCategoryId(Long categoryId) { return productRepository.findByCategoryId(categoryId); }



}

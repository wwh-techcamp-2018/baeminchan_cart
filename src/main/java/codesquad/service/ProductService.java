package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.dto.CartProductDTO;
import codesquad.dto.RequestCartProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 품목이 없습니다."));
    }


    public CartProductDTO getProductInfo(RequestCartProductDTO requestCartProductDTO) {
        Product savedProduct = findById(requestCartProductDTO.getProductId());
        return savedProduct.toProductDTO(requestCartProductDTO.getCount());
    }

}

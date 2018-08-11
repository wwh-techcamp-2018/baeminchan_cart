package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.dto.BasketDTO;
import codesquad.dto.BasketProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<BasketProductDTO> getBasketProduct(List<BasketDTO> basketDtoList) {
        List<BasketProductDTO> basketProductDtos = new ArrayList<>();

        basketDtoList.forEach(basketDTO -> basketProductDtos.add(BasketProductDTO.createBasketProductDto(productRepository.
                findById(basketDTO.getId()).get(),basketDTO.getEa())));
        return basketProductDtos;
    }
}

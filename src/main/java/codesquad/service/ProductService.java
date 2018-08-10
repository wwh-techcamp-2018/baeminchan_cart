package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.dto.BasketDto;
import codesquad.dto.BasketProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<BasketProductDto> getBasketProduct(List<BasketDto> basketDtoList) {
        List<BasketProductDto> basketProductDtos = new ArrayList<>();
        if(basketDtoList == null){
            throw new RuntimeException();
        }

        for (BasketDto basketDto: basketDtoList) {
             basketProductDtos.add(BasketProductDto.createBasketProductDto(productRepository.
                     findById(basketDto.getId()).get(),basketDto.getEa()));
        }
        return basketProductDtos;
    }
}

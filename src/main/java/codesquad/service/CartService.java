package codesquad.service;

import codesquad.domain.*;
import codesquad.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;


    public Cart create(User user) {
        return cartRepository.save(Cart.builder().user(user).build());
    }

    @Transactional
    public Cart updateProductInCart(Cart cart, Product product, Integer productNum) {
        cart.updateProductNum(product.getId(), productNum);
        return cartRepository.save(cart);
    }

    public List<Product> getProducts(Cart cart) {
        if (!cart.isEmpty()) {
            return cart.productsIdList().stream().map((id) -> findByProductId(id)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private Product findByProductId(Long id) {
        // Todo: Add ControllerAdvice and error responseModel
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("반찬이 존재하이 않습니다."));
    }
}



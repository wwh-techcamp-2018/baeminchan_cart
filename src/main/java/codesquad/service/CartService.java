package codesquad.service;

import codesquad.common.Message;
import codesquad.domain.*;
import codesquad.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    public Cart create(User user) {
        return cartRepository.save(Cart.builder().user(user).build());
    }

    public Cart updateProduct(Cart cart, Long productId, Integer productNum) {
        cart.updateProductNum(productId, productNum);
        return cartRepository.save(cart);
    }

    public Cart addProduct(Cart cart, Long productId, Integer productNum) {
        cart.updateProductNum(productId, cart.productNum(productId) + productNum);
        return cartRepository.save(cart);
    }

    public List<Product> getProducts(Cart cart) {
        if (!cart.isEmpty()) {
            return cart.productsIdList().stream().map((id) -> findByProductId(id)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void deleteProduct(Cart cart, Long productId) {
        cart.deleteProduct(productId);
        cartRepository.save(cart);
    }

    public void deleteMultiProducts(Cart cart, List<String> productIds) {
        cart.deleteMultiProducts(convertStringToLong(productIds));
        cartRepository.save(cart);
    }

    private List<Long> convertStringToLong(List<String> productIds) {
        return productIds.stream().map((id) -> Long.parseLong(id)).collect(Collectors.toList());
    }

    private Product findByProductId(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(messageSourceAccessor.getMessage(Message.NOT_EXIST_PRODUCT)));
    }
}



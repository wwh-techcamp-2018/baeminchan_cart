package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartProductDTO;
import codesquad.enums.CartItemState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CartProductHistoryRepository cartProductHistoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 품목이 없습니다."));
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return findById(productId);
    }

    @Transactional
    public void saveCartItem(List<CartProductDTO> cartProductDTOS, User loginUser) {
        log.debug("cartItemDTOs : {}, loginUser : {}", cartProductDTOS, loginUser);
        cartProductDTOS.forEach(cartItemDTO -> {
            cartProductRepository.save(cartItemDTO.toCartItem(loginUser));
            cartProductHistoryRepository.save(new CartProductHistory(cartItemDTO.getProduct(), cartItemDTO.getCount(), loginUser, CartItemState.KEEP, LocalDateTime.now()));
        });
        removeCartProductsFromSession(cartProductDTOS);
    }

    private void removeCartProductsFromSession(List<CartProductDTO> cartItemDTOs) {
        cartItemDTOs.removeAll(cartItemDTOs);
    }

    @Transactional
    public void deleteCartItem(Product product, User loginUser) {
        CartProduct cartItem = cartProductRepository.findByUserAndProduct(loginUser, product).orElseThrow(() -> new IllegalArgumentException("삭제시킬 상품이 존재하지 않습니다."));
        cartItem.delete();
        CartProductHistory cartItemHistory = cartProductHistoryRepository.findByUserAndProduct(loginUser, product).orElseThrow(() -> new IllegalArgumentException("삭제시킬 상품에 대한 이력이 존재하지 않습니다."));
        cartItemHistory.recordDeleteState();
    }
}

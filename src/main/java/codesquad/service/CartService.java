package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

//    public Cart addCart(Long productId, int count) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
//        Cart cart = new Cart(product, count);
//        carts.add(cart);
//        return cart;
//    }
}

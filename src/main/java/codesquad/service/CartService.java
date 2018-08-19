package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.domain.ProductRepository;
import codesquad.dto.CartProductDto;
import codesquad.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    private static final Long DELIVERY_PRICE_REFERENCE = 40_000L;
    private static final Long DELIVERY_PRICE = 2_500L;

    @Transactional
    public Cart create() {
        return cartRepository.save(Cart.builder().build());
    }

    @Transactional
    public Cart add(Long cartId, Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("반찬이 존재하지 않습니다.");
        }
        Cart cart = findById(cartId);
        cart.addProduct(productId, 1);
        return cartRepository.save(cart);
    }

    public Cart findById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("장바구니가 존재하지 않습니다."));
    }

    public List<CartProductDto> getCartProducts(Cart cart) {
        return getCartProductsListFrom(cart.getProducts());
    }

    public Long computeCartTotalPrice(List<CartProductDto> cartProducts) {
        Long cartTotalPrice = 0L;
        for (CartProductDto dto : cartProducts) {
            cartTotalPrice += dto.getTotalPrice();
        }
        return cartTotalPrice;
    }

    private List<CartProductDto> getCartProductsListFrom(HashMap<Long, Integer> products) {
        List<CartProductDto> cartProducts = new ArrayList<>();
        products.forEach(
                (k, v) -> cartProducts.add(CartProductDto.from(productRepository.findById(k)
                        .orElseThrow(() -> new ResourceNotFoundException("반찬이 존재하지 않습니다.")), v)));
        return cartProducts;
    }

    public Long getDeliveryPrice(Long productTotalPrice) {
        return (productTotalPrice >= DELIVERY_PRICE_REFERENCE) ? 0L : DELIVERY_PRICE;
    }
}

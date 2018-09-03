package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    public Cart getCart() {
        return Cart.getInstance();
    }

    public void addProduct(Product product, int buyCount) {
        product.calculatePrice(buyCount);
        Cart cartDTO = Cart.getInstance();
        cartDTO.addProduct(product);
    }
}

package codesquad.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartProduct> cartProducts = new ArrayList<>();

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public int add(Long productId, int quantity) {
        if(cartProducts.stream().filter(cartProduct -> cartProduct.equals(productId)).findFirst().isPresent()) {
            cartProducts.stream().filter(cartProduct -> cartProduct.equals(productId)).findFirst().get().add(quantity);
            return cartProducts.size();
        }
        cartProducts.add(new CartProduct(productId, quantity));
        return cartProducts.size();
    }

}

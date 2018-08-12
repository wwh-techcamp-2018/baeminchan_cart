package codesquad.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@ToString
public class Cart {
    private List<CartProduct> cartProducts = new ArrayList<>();

    public void addCartProduct(CartProduct cartProduct) {
        Optional<CartProduct> addedProduct = cartProducts.stream()
                .filter(product -> product.getProduct().equals(cartProduct.getProduct())).findFirst();

        if(addedProduct.isPresent()) {
            cartProducts.remove(addedProduct);
        }
        cartProducts.add(cartProduct);
    }
}

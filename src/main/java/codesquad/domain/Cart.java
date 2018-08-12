package codesquad.domain;

import java.util.LinkedHashMap;
import java.util.Optional;

public class Cart {

    private LinkedHashMap<Long, Integer> products;

    public Cart() {
        products = new LinkedHashMap<>();
    }

    public LinkedHashMap<Long, Integer> getProducts() {
        return products;
    }

    public void addProduct(Long productId, Integer count) {
        Integer countBefore = Optional.ofNullable(products.get(productId)).orElse(0);
        products.put(productId, countBefore + count);
    }

    public void updateProduct(Long productId, Integer count) {
        products.put(productId, count);
    }

    public void deleteProduct(Long productId, Integer count) {
        Optional.ofNullable(products.get(productId))
                .ifPresent(productCount -> {
                    if (count == null || productCount <= count) {
                        products.remove(productId);
                        return;
                    }
                    products.put(productId, productCount - count);
                });
    }
}

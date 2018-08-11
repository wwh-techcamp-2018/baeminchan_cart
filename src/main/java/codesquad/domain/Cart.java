package codesquad.domain;

import java.util.*;

public class Cart {

    private Map<Long, Integer> products;

    public Cart() {
        products = new LinkedHashMap<>();
    }

    public void addProduct(Long productId) {
        addProduct(productId, 1);
    }

    public void addProduct(Long productId, int count) {
        Integer countBefore = Optional.ofNullable(products.get(productId)).orElse(0);
        products.put(productId, countBefore + count);
    }

    public Map<Long, Integer> getProducts() {
        return products;
    }

    public void deleteProduct(Long productId, int count) {
        Optional.ofNullable(products.get(productId)).ifPresent(productCount -> {
            if (productCount.intValue() - count == 0) {
                products.remove(productId);
                return;
            }
            products.put(productId, productCount - count);
        });
    }
}

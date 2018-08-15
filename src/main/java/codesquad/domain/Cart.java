package codesquad.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class Cart {

    private LinkedHashMap<Long, Integer> products;

    public Cart() {
        products = new LinkedHashMap<>();
    }

    public LinkedHashMap<Long, Integer> getProducts() {
        return products;
    }

    public List<Long> getProductIds() {
        return new ArrayList<>(products.keySet());
    }

    public Integer getProductCount(Long id) {
        return products.getOrDefault(id, 0);
    }

    public void addProduct(Long id) {
        Integer productCount = products.getOrDefault(id, 0);
        products.put(id, productCount + 1);
    }

    public void addProduct(Long id, Integer count) {
        if (count == null) {
            addProduct(id);
            return;
        }
        if (count < 1) {
            throw new IllegalArgumentException();
        }

        Integer productCount = products.getOrDefault(id, 0);
        products.put(id, productCount + count);
    }

    public void updateProduct(Long id, Integer count) {
        if (count == 0) {
            deleteProduct(id);
            return;
        }
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        products.put(id, count);
    }

    public void deleteProduct(Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        products.remove(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }
}

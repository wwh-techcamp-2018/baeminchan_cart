package codesquad.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public Cart addItem(Product product) {
        return addItem(product, 1);
    }

    public Cart addItem(Product product, int count) {
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + count);
        } else {
            items.put(product, count);
        }

        return this;
    }

    public int getItemSize() {
        return items.size();
    }

    public long calculate() {
        long price = 0;

        for (Map.Entry<Product, Integer> itemEntry : items.entrySet()) {
            price += itemEntry.getKey().calculate(itemEntry.getValue());
        }

        if (price < 40000) {
            return price + 2500;
        }

        return price;
    }
}

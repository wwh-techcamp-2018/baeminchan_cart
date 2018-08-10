package codesquad.domain;

import codesquad.dto.CartRowDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Cart {
    private static final int DISCOUNT_THRESHOLD_COUNT = 10;
    private static final Long DISCOUNT_THRESHOLD_RATIO = 20L;
    private static final double MANY_DISCOUNT_RATIO = 0.95;

    @JsonIgnore
    private Map<Product, Long> products;

    public static Cart create() {
        return new Cart(new HashMap<>());
    }

    public static Cart create(Product product) {
        return Cart.create().add(product);
    }

    private Cart(Map<Product, Long> products) {
        this.products = products;
    }

    public Cart add(Product product) {
        this.add(product, 1L);
        return this;
    }

    public Cart add(Product product, Long count) {
        Long i = products.getOrDefault(product, 0L);
        products.put(product, i + count);
        return this;
    }

    @JsonProperty
    public Long totalSize() {
        return products.values().stream().mapToLong(t -> t).sum();
    }

    public Set<Product> getProducts() {
        return products.keySet();
    }

    public void remove(Product product) {
        Long i = this.products.getOrDefault(product, 1L);
        products.put(product, i - 1);
        if (i == 1L) products.remove(product);
    }

    public HashMap<Product, Long> productsPrice() {
        HashMap<Product, Long> map = new HashMap<>();
        for (Map.Entry<Product, Long> entry : products.entrySet()) {
            Product p = entry.getKey();
            Long cnt = entry.getValue();
            Long productTotalPrice = calcProductTotalPrice(p, cnt);
            map.put(p, productTotalPrice);
        }
        return map;
    }

    private Long calcProductTotalPrice(Product p, Long cnt) {
        Long discountRatio = (p.getDiscountRatio() == null ) ? 0L : p.getDiscountRatio();
        Long totalPrice = discountedPrice(p, cnt);

        if(discountRatio < DISCOUNT_THRESHOLD_RATIO && cnt >= DISCOUNT_THRESHOLD_COUNT)
            totalPrice = (long) (totalPrice * MANY_DISCOUNT_RATIO);
        return totalPrice;
    }

    private Long discountedPrice(Product p, Long cnt) {
        return (p.getDiscountRatio() == null) ? p.getPrice() * cnt : (p.getPrice() * cnt * (100 - p.getDiscountRatio())) / 100;

    }

    @JsonProperty
    public long totalPrice() {
        return this.productsPrice().values().stream().mapToLong(t -> t).sum();
    }

    @JsonProperty(value = "products")
    public List<CartRowDTO> serialize(){
        List<CartRowDTO> list = new ArrayList<>();

        for (Map.Entry<Product, Long> entry : productsPrice().entrySet()) {
            list.add(CartRowDTO.create(entry, products.get(entry.getKey())));
        }
        return list;
    }

    @JsonProperty
    public long deliveryPrice() {
        return (totalPrice() >= 40000) ? 0L : 2500L;
    }


}

package codesquad.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<>();

    private int productPrice = 0;

    private int deliveryPrice = 0;

    private int totalPrice = 0;

    private static Cart instance = new Cart();

    private Cart() { }

    public static Cart getInstance () {
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getDeliveryPrice() {
        if(productPrice < 40000) {
            deliveryPrice = 2500;
        }
        return deliveryPrice;
    }

    public int getTotalPrice() {
        totalPrice = productPrice + deliveryPrice;
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(Product product) {
        products.add(product);
        productPrice += product.getTotalPrice();
    }
}

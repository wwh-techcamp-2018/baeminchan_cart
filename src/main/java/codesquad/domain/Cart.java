package codesquad.domain;

import java.util.List;
import java.util.Objects;

public class Cart {

    private Product product;
    private int amount;

    public Cart(){
    }

    public Cart(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static List<Cart> addCart(List<Cart> carts, Cart cart){
        if(carts.contains(cart)) {
            carts.stream()
                    .filter(elem -> elem.product.equals(cart.product))
                    .forEach(elem -> elem.addAmount(cart.amount));
            return carts;
        }
        carts.add(cart);
        return carts;
    }

    public static List<Cart> removeProduct(List<Cart> carts, Product product){
        carts.stream()
                .filter(elem -> elem.product.equals(product))
                .forEach(elem -> carts.remove(elem));
        return carts;
    }

    public void addAmount(int amount){
        this.amount += amount;
    }

    public long calculatePrice(){
        return product.calculatePrice(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(product, cart.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}

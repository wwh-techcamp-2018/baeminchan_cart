package codesquad.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final long DELIVERY_FEE = 2500;
    private static final long PRICE_NEEDED_DELEVERY_FEE = 40000;

    private List<Cart> carts;
    private long totalPrice;

    public Order() {
        carts = new ArrayList<Cart>();
    }

    public Order(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addCart(Cart cart){
        carts = Cart.addCart(carts, cart);
        updatePrice();
    }

    public void removeProduct(Product product){
        carts = Cart.removeProduct(carts, product);
        updatePrice();
    }

    private void updatePrice(){
        totalPrice = 0;
        carts.forEach(elem -> totalPrice += elem.calculatePrice());
        if(isNeedDeliveryFee()) totalPrice += DELIVERY_FEE;
    }
    
    private boolean isNeedDeliveryFee(){
        return totalPrice < PRICE_NEEDED_DELEVERY_FEE;
    }

    @Override
    public String toString() {
        return "Order{" +
                "carts=" + carts +
                '}';
    }
}

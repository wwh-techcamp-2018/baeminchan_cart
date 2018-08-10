package codesquad.domain;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {

    @Test
    public void addOnce() {
        Cart cart = Cart.create(new Product());
        assertThat(cart.totalSize()).isEqualTo(1);
    }

    @Test
    public void addTwice() {
        Product product = new Product();
        Cart cart = Cart.create();
        cart.add(product);
        cart.add(product);

        assertThat(cart.totalSize()).isEqualTo(2);
        assertThat(cart.getProducts().size()).isEqualTo(1);
    }

    @Test
    public void addDifference() {
        Product p1 = Product.builder().title("p1").price(3000L).build();
        Product p2 = Product.builder().title("p2").price(1000L).build();
        Cart cart = Cart.create();
        cart.add(p1);
        cart.add(p2);

        assertThat(cart.totalSize()).isEqualTo(2);
        assertThat(cart.getProducts().size()).isEqualTo(2);
    }

    @Test
    public void removeOne() {
        Product product = new Product();
        Cart cart = Cart.create(product);
        cart.remove(product);

        assertThat(cart.totalSize()).isEqualTo(0);
        assertThat(cart.getProducts().size()).isEqualTo(0);
    }

    @Test
    public void removeTwo() {
        Product product = new Product();
        Cart cart = Cart.create();
        cart.add(product, 3L);
        cart.remove(product);
        cart.remove(product);

        assertThat(cart.totalSize()).isEqualTo(1);
        assertThat(cart.getProducts().size()).isEqualTo(1);
    }

    @Test
    public void removeDuplicate() {
        Product product = new Product();
        Cart cart = Cart.create(product);
        cart.remove(product);
        cart.remove(product);

        assertThat(cart.totalSize()).isEqualTo(0);
        assertThat(cart.getProducts().size()).isEqualTo(0);
    }

    @Test
    public void calcOneProduct() {
        Product product = Product.builder().price(3000L).build();
        Cart cart = Cart.create();
        cart.add(product, 3L);
        assertThat(cart.totalPrice()).isEqualTo(9000L);
    }

    @Test
    public void calcTwoProduct() {
        Product p1 = Product.builder().title("p1").price(3000L).build();
        Product p2 = Product.builder().title("p2").price(1000L).build();
        Cart cart = Cart.create();
        cart.add(p1, 3L);
        cart.add(p2, 4L);

        assertThat(cart.totalPrice()).isEqualTo(p1.getPrice()*3 + p2.getPrice()*4);
    }

    @Test
    public void calcTenProduct() {
        Product product = Product.builder().price(3000L).discountRatio(10L).build();
        Cart cart = Cart.create();
        cart.add(product, 10L);
        assertThat(cart.totalPrice()).isEqualTo(25650L);
    }

    @Test
    public void calcDiscountProduct() {
        Product product = Product.builder().price(3000L).discountRatio(20L).build();
        Cart cart = Cart.create();
        cart.add(product, 3L);
        assertThat(cart.totalPrice()).isEqualTo(7200L);
    }

    @Test
    public void calcTenWithHighDiscountProduct() {
        Product product = Product.builder().price(3000L).discountRatio(30L).build();
        Cart cart = Cart.create();
        cart.add(product, 10L);
        assertThat(cart.totalPrice()).isEqualTo(21000L);
    }

    @Test
    public void testComplicated() {
        Product p1 = Product.builder().title("p1").price(3000L).build();
        Product p2 = Product.builder().title("p2").price(1000L).build();
        Cart cart = Cart.create();
        cart.add(p1, 13L);
        cart.add(p2, 5L);
        assertThat(cart.totalPrice()).isEqualTo(42050L);
    }

    @Test
    public void deliveryPrice() {
        Product p1 = Product.builder().title("p1").price(3000L).build();
        Product p2 = Product.builder().title("p2").price(1000L).build();
        Cart cart = Cart.create();
        cart.add(p2, 5L);
        assertThat(cart.deliveryPrice()).isEqualTo(2500L);
        cart.add(p1, 40L);
        assertThat(cart.deliveryPrice()).isEqualTo(0L);
    }
}

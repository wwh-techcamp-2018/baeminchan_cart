package codesquad.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CartTest {


    @Test
    public void addSelectedItemTest() {
        Cart cart = new Cart();
        Product product = new Product();
        SelectedItem selectedItem = new SelectedItem(product, 1L);
        SelectedItem selectedItemOfSameProduct = new SelectedItem(product, 2L);

        cart.addSelectedItem(selectedItem);
        cart.addSelectedItem(selectedItemOfSameProduct);

        assertThat(cart.getSelectedItems().size()).isEqualTo(1);
        assertThat(cart.getSelectedItems().get(0).getCount()).isEqualTo(3);

    }

    @Test
    public void getTotalSalesPrice() {
        Product product = new Product("title", "description", "img", 5000L);
        SelectedItem selectedItem = new SelectedItem(product, 10L);
        Cart cart = new Cart();
        cart.addSelectedItem(selectedItem);
        assertThat(cart.getTotalSalesPrice()).isEqualTo(47500);
    }

    @Test
    public void getShipmentPrice() {
        Product product = new Product("title", "description", "img", 5000L);
        SelectedItem selectedItem = new SelectedItem(product, 10L);
        Cart cart = new Cart();
        cart.addSelectedItem(selectedItem);
        assertThat(cart.getShipmentPrice()).isEqualTo(0);
    }


}
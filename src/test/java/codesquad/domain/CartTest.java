package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CartTest {


    private Product product;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        product = new Product("title", "description", "img", 5000L);
        SelectedItem selectedItem = new SelectedItem(product, 10L);
        cart.addSelectedItem(selectedItem);
    }
    @Test
    public void getSelectedItemOfSameProduct() {
        SelectedItem selectedItemOfSameProduct = new SelectedItem(product, 2L);
        assertThat(cart.getSelectedItemOfSameProduct(selectedItemOfSameProduct).get().getProduct())
                .isEqualTo(product);
    }

    @Test
    public void getSelectedItemById() {
        assertThat(cart.getSelectedItemById(1L).get().getId())
                .isEqualTo(1L);
    }

    @Test
    public void addSelectedItemTest() {
        SelectedItem selectedItemOfSameProduct = new SelectedItem(product, 2L);
        cart.addSelectedItem(selectedItemOfSameProduct);
        assertThat(cart.getSelectedItems().size()).isEqualTo(1);
        assertThat(cart.getSelectedItems().get(0).getCount()).isEqualTo(12L);
    }

    @Test
    public void generateSelectedItemId() {
        assertThat(cart.generateSelectedItemId()).isEqualTo(2L);
    }

    @Test
    public void changeSelectedItemCount() {
        cart.changeSelectedItemCount(1L, 20L);
        assertThat(cart.getSelectedItemById(1L).get().getCount()).isEqualTo(20L);
    }

    @Test
    public void removeSelectedItem() {
        cart.removeSelectedItem(1L);
        assertThat(cart.getSelectedItemById(1L).isPresent()).isEqualTo(false);
    }

    @Test
    public void getTotalSalesPrice() {
        assertThat(cart.getTotalSalesPrice()).isEqualTo(47500);
    }

    @Test
    public void getShipmentPrice() {
        assertThat(cart.getShipmentPrice()).isEqualTo(0);
    }


}
package codesquad.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SelectedItemTest {

    @Test
    public void isSameProduct() {
        Product product = new Product();
        SelectedItem selectedItem = new SelectedItem(product, 1L);
        SelectedItem selectedItemOfSameProduct = new SelectedItem(product, 2L);
        assertThat(selectedItem.isSameProduct(selectedItemOfSameProduct)).isEqualTo(true);
    }

    @Test
    public void calculateTotalPrice() {
        Product product = new Product("title", "description", "img", 5000L);
        SelectedItem selectedItem = new SelectedItem(product, 10L);
        assertThat(selectedItem.getTotalPrice()).isEqualTo(47500);
    }

}
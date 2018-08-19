package codesquad.domain;

import codesquad.dto.CartProductDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartProductDtoTest {

    @Test
    public void from() {
        Product product = Product.builder().title("zipBop").price(3000L).build();
        CartProductDto dto = CartProductDto.builder().build();

        assertThat(dto.from(product, 2).getTitle()).isEqualTo(product.getTitle());
        assertThat(dto.from(product, 2).getTotalPrice()).isEqualTo(6000L);
    }

    @Test
    public void add_ten_same_products_under_twenty_percent_discount() {
        CartProductDto dto = CartProductDto.builder().build();
        Product product = Product.builder().title("zipBop").price(3000L).build();

        double discountedPrice = (double) product.getPrice() * 0.95;
        assertThat(dto.from(product, 10).getPrice()).isEqualTo((long) discountedPrice);
    }

    @Test
    public void add_ten_same_products_over_twenty_percent_discount() {
        CartProductDto dto = CartProductDto.builder().build();
        Product product = Product.builder().title("zipBop").discountRatio(0.2).price(3000L).build();

        double discountedPrice = (double) product.getPrice() * (1 - product.getDiscountRatio());
        assertThat(dto.from(product, 10).getPrice()).isEqualTo((long) discountedPrice);
    }
}

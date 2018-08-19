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
}

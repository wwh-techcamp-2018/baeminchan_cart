package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPriceDTOTest {
    @Test
    public void create_cartDTO() {
        // Given
        LinkedHashMap<Long, Integer> productIds = new LinkedHashMap<>();
        Product product = Product.builder().price(2_000L).build();
        productIds.put(product.getId(), 5);
        Cart cart = new Cart(productIds);

        // When
        CartPriceDTO dto = CartPriceDTO.from(cart, Arrays.asList(product));

        // Then
        assertThat(dto.getDeliveryCharge()).isEqualTo(2_500L);
        assertThat(dto.getTotalPrice()).isEqualTo(12_500L);
    }

    @Test
    public void create_empty_cartDTO() {
        //Given
        Cart cart = Cart.builder().build();

        // When
        CartPriceDTO dto = CartPriceDTO.from(cart, Arrays.asList());

        // Then
        assertThat(dto.getDeliveryCharge()).isEqualTo(0L);
        assertThat(dto.getTotalPrice()).isEqualTo(0L);
    }
}

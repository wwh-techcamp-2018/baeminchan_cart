package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPriceDTOTest {
    @Test
    public void create_cartDTO() {
        // Given
        HashMap<Long, Integer> productIds = new HashMap<>();
        productIds.put(Product.builder().price(2_000L).build().getId(), 5);

        Cart cart = new Cart(productIds);
        cart.setProductsTotalPrice(10_000L);
        cart.setDeliveryCharge(2_500L);

        // When
        CartPriceDto dto = CartPriceDto.from(cart);

        // Then
        assertThat(dto.getDeliveryCharge()).isEqualTo(2_500L);
        assertThat(dto.getTotalPrice()).isEqualTo(12_500L);
    }

    @Test
    public void create_empty_cartDTO() {
        //Given
        Cart cart = Cart.builder().build();

        // When
        CartPriceDto dto = CartPriceDto.from(cart);

        // Then
        assertThat(dto.getDeliveryCharge()).isEqualTo(0L);
        assertThat(dto.getTotalPrice()).isEqualTo(0L);
    }
}

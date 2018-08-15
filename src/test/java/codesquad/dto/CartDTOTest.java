package codesquad.dto;

import codesquad.domain.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartDTOTest {

    private List<CartProductDTO> cartProducts;
    private CartDTO cartDTO;

    @Test
    public void noProducts() {
        cartDTO = new CartDTO();
        assertThat(cartDTO.getSumPrice()).isEqualTo(0);
        assertThat(cartDTO.getShippingFee()).isEqualTo(2500);
        assertThat(cartDTO.getTotalPrice()).isEqualTo(0);
    }

    @Test
    public void priceWithShippingFee() {
        cartProducts = new ArrayList<>();
        cartProducts.add(new CartProductDTO(Product.builder().price(1000).discountRate(0D).build(), 5));
        cartDTO = new CartDTO(cartProducts);

        assertThat(cartDTO.getSumPrice()).isEqualTo(5000);
        assertThat(cartDTO.getShippingFee()).isEqualTo(2500);
        assertThat(cartDTO.getTotalPrice()).isEqualTo(7500);
    }

    @Test
    public void priceWithoutShippingFee() {
        cartProducts = new ArrayList<>();
        cartProducts.add(new CartProductDTO(Product.builder().price(10000).discountRate(0D).build(), 4));
        cartDTO = new CartDTO(cartProducts);

        assertThat(cartDTO.getSumPrice()).isEqualTo(40000);
        assertThat(cartDTO.getShippingFee()).isEqualTo(0);
        assertThat(cartDTO.getTotalPrice()).isEqualTo(40000);
    }

}
package codesquad.dto;

import codesquad.domain.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartProductDTOTest {

    private Product product;

    @Test
    public void getSalesPriceExtraDiscount() {
        product = Product.builder()
                .price(10000)
                .discountRate(10D)
                .build();
        CartProductDTO cartProductDTO = new CartProductDTO(product, 10);
        assertThat(cartProductDTO.getSumPrice()).isEqualTo(85000);
    }

    @Test
    public void getSalesPriceNoNExtraDiscount() {
        product = Product.builder()
                .price(10000)
                .discountRate(20D)
                .build();
        CartProductDTO cartProductDTO = new CartProductDTO(product, 10);
        assertThat(cartProductDTO.getSumPrice()).isEqualTo(80000);
    }

}
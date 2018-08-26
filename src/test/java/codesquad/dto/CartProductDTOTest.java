package codesquad.dto;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CartProductDTOTest {
    @Test
    public void from() {
        // Given
        Product product = Product.builder()
                .title("zipBop").price(3_000L).discountRatio(0.5).build();
        HashMap<Long, Integer> productIds = new HashMap<>();
        productIds.put(product.getId(), 3);
        Cart cart = new Cart(productIds);

        // When
        CartProductDto dto = CartProductDto.from(cart, product);

        // Then
        assertThat(dto.getTitle()).isEqualTo(product.getTitle());
        assertThat(dto.getTotalPrice()).isEqualTo(4_500L);
    }

    @Test
    public void listFrom() {
        //Given
        Product product = Product.builder()
                .title("zipBop").price(3_000L).discountRatio(0.5).build();
        Product otherProduct = Product.builder()
                .title("Banchan").price(5_000L).discountRatio(0.1).build();
        HashMap<Long, Integer> productIds = new HashMap<>();
        productIds.put(product.getId(), 3);
        productIds.put(otherProduct.getId(), 1);
        Cart cart = new Cart(productIds);

        // When
        List<CartProductDto> dtoList = CartProductDto.listFrom(cart, Arrays.asList(product, otherProduct));

        // Then
        assertThat(dtoList.stream().map((e) -> e.getTitle()).collect(Collectors.toList())).containsExactly(product.getTitle(), otherProduct.getTitle());
    }
}

package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.domain.Product;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartControllerTest extends AcceptanceTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void addProduct() {
        // Given
        Product productOne = Product.builder().build();
        // When
        ResponseEntity<Cart> response = template().postForEntity("/api/cart/1", productOne.getId(), Cart.class);

        //Then
        assertThat(response.getBody().getProductList().size()).isEqualTo(1L);
    }
}

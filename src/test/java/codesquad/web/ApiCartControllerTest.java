package codesquad.web;

import codesquad.dto.CartItem;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCartControllerTest extends AcceptanceTest {
    @Test
    public void holdItemToCart() {

        CartItem cartItem = CartItem.builder()
                .amount(Long.valueOf(10))
                .productId(Long.valueOf(5))
                .build();

        ResponseEntity<Void> response = template().postForEntity("/cart/hold", cartItem, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

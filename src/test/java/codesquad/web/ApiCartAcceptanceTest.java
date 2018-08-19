package codesquad.web;

import codesquad.dto.CartItemDTO;
import codesquad.support.test.AcceptanceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCartAcceptanceTest extends AcceptanceTest {
    @Test
    public void save() {
        CartItemDTO cartItemDTO = CartItemDTO.builder()
                .productId(1L)
                .quantity(1)
                .build();
        ResponseEntity<Void> response = template().postForEntity("/api/products/cart", cartItemDTO, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
//
//    @Test
//    public void count(){
//        ResponseEntity<Integer> response = template().getForEntity("/products/cart/count", Integer.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
//    }
}

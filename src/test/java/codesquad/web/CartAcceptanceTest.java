package codesquad.web;

import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class CartAcceptanceTest extends AcceptanceTest {
    @Test
    public void putInCart() {
        ResponseEntity<Integer> response = template().postForEntity("/carts/1", null, Integer.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(1);
    }

    @Test
    public void getCartNum() {
        ResponseEntity<Integer> response = template().getForEntity("/carts/size", Integer.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(0);
    }
}

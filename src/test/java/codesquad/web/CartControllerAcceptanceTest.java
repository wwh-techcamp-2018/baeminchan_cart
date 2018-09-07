package codesquad.web;

import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class CartControllerAcceptanceTest extends AcceptanceTest {
    @Test
    public void cart() {
        ResponseEntity<String> response = template().getForEntity("/cart", String.class);

        assertThat(response.getBody()).contains("장바구니");

    }
}

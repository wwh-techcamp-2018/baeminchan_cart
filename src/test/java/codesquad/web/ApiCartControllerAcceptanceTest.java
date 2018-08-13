package codesquad.web;

import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCartControllerAcceptanceTest extends AcceptanceTest {

    private String cookie;

    @Before
    public void setUp() throws Exception {
        ResponseEntity<Void> response = putCart(1L, 2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        cookie = response.getHeaders().getFirst(response.getHeaders().SET_COOKIE);
    }

    @Test
    public void updateCart() {
        ResponseEntity<Void> response = null;

        response = putCart(1L, 2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = putCart(1L, -1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = putCart(1L, 0);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteCart() {
        ResponseEntity<Void> response = null;

        response = putCart(2L, 2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = deleteCart(2L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        response = deleteCart(2L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RestResponse> getCount() {
        return template().getForEntity("/api/cart/count", RestResponse.class);
    }

    private ResponseEntity<Void> putCart(Long id, Integer count) {
        String endPoint = String.format("/api/cart/products/%d?count=%d", id, count);
        return template().exchange(endPoint, HttpMethod.PUT, applicaionJsonHeader(), Void.class);
    }

    private ResponseEntity<Void> deleteCart(Long id) {
        String endPoint = String.format("/api/cart/products/%d", id);
        return template().exchange(endPoint, HttpMethod.DELETE, applicaionJsonHeader(), Void.class);
    }

    private HttpEntity applicaionJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        if (cookie != null) {
            headers.add("Cookie", cookie);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(null, headers);
    }

}

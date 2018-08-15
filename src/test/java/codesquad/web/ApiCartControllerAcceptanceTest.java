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
        ResponseEntity<RestResponse> response = putCart(1L, 2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualTo(1);
        cookie = response.getHeaders().getFirst(response.getHeaders().SET_COOKIE);
    }

    @Test
    public void addProductsToCart() {
        ResponseEntity<RestResponse> response = null;

        response = addCart(1L, -1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = addCart(1L, 0);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = addCart(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualTo(1);

        response = addCart(1L, 2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualTo(1);
    }

    @Test
    public void updateProductsInCart() {
        ResponseEntity<RestResponse> response = null;

        response = putCart(1L, -1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = putCart(1L, 0);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualTo(0);
    }

    @Test
    public void deleteProductInCart() {
        ResponseEntity<Void> response = null;

        response = deleteCart(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        response = deleteCart(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<RestResponse> getCount() {
        return template().getForEntity("/api/cart/products/count", RestResponse.class);
    }

    private ResponseEntity<RestResponse> addCart(Long id) {
        String endPoint = String.format("/api/cart/products/%d", id);
        return template().exchange(endPoint, HttpMethod.POST, applicationJsonHeader(), RestResponse.class);
    }

    private ResponseEntity<RestResponse> addCart(Long id, Integer count) {
        String endPoint = String.format("/api/cart/products/%d?count=%d", id, count);
        return template().exchange(endPoint, HttpMethod.POST, applicationJsonHeader(), RestResponse.class);
    }

    private ResponseEntity<RestResponse> putCart(Long id, Integer count) {
        String endPoint = String.format("/api/cart/products/%d?count=%d", id, count);
        return template().exchange(endPoint, HttpMethod.PUT, applicationJsonHeader(), RestResponse.class);
    }

    private ResponseEntity<Void> deleteCart(Long id) {
        String endPoint = String.format("/api/cart/products/%d", id);
        return template().exchange(endPoint, HttpMethod.DELETE, applicationJsonHeader(), Void.class);
    }

    private HttpEntity applicationJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        if (cookie != null) {
            headers.add("Cookie", cookie);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(null, headers);
    }

}

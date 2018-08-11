package codesquad.web;

import codesquad.dto.CartDto;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCartAcceptanceTest extends AcceptanceTest {

    @Test
    public void addToCart() {
        ResponseEntity<Map<String, Object>> response = jsonRequest("/api/carts", HttpMethod.POST, new CartDto(1L, 3), new ParameterizedTypeReference<Map<String, Object>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().get("totalKind")).isEqualTo(1);
        assertThat(response.getBody().get("productTitle")).isEqualTo("여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리");
    }

    @Test
    public void getCartSize() {
        ResponseEntity<Map<String, Object>> response = jsonRequest("/api/carts", HttpMethod.POST, new CartDto(2L, 3), new ParameterizedTypeReference<Map<String, Object>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().get("totalKind")).isEqualTo(1);
        assertThat(response.getBody().get("productTitle")).isEqualTo("이번 주 매콤한 메인요리로 딱!");
    }

    private <T> ResponseEntity<T> jsonRequest(String url, HttpMethod method, Object body, ParameterizedTypeReference<T> parameterizedTypeReference) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return template().exchange(url, method, new HttpEntity<>(body, headers), parameterizedTypeReference);
    }
}

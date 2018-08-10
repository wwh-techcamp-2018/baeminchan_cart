package codesquad.web;

import codesquad.dto.CartDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ApiCartControllerTest extends AcceptanceTest {

    @Test
    public void create() {
        CartDto cartDto = new CartDto(1L, 10L);
        ResponseEntity<Void> response = template().postForEntity("/carts", cartDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
package codesquad.web;

import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCartControllerTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiCartControllerTest.class);

    @Test
    public void 카트_상품_추가() {
        ResponseEntity<Long> response = template().postForEntity("/api/cart/1", 1, Long.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("response.getBody().toString() : {}", response.getBody().toString());
    }

}
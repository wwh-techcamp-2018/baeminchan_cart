package codesquad.web;


import codesquad.domain.Merchandise;
import codesquad.dto.CartDTO;
import codesquad.exception.RestResponse;
import codesquad.service.ProductService;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMerchandiseControllerTest extends AcceptanceTest {

    @Test
    public void create() {
        CartDTO cartDto = new CartDTO(1L, 2);
        ResponseEntity<RestResponse> response = template().postForEntity("/api/merchandise", cartDto, RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
package codesquad.web;


import codesquad.domain.Merchandise;
import codesquad.exception.RestResponse;
import codesquad.service.ProductService;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMerchandiseControllerTest extends AcceptanceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void create() {
        Merchandise merchandise = new Merchandise(2, false, productService.findById(1));
        ResponseEntity<RestResponse> response = template().postForEntity("/api/merchandise", merchandise, RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
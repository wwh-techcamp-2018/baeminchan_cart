package codesquad.web;

import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductControllerAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ProductControllerAcceptanceTest.class);
    
    @Test
    public void products() {
        ResponseEntity<String> responseEntity = template().getForEntity("/products", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO : fix NPE
        //assertThat(responseEntity.getHeaders().getLocation().getPath()).contains("products");
    }

    @Test
    public void product() {
        // NOTE : product.id = 1 있다고 가정
        ResponseEntity<String> responseEntity = template().getForEntity("/product/1", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO : fix NPE
        //assertThat(responseEntity.getHeaders().getLocation().getPath()).contains("product");
    }
}

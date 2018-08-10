package codesquad.web;

import codesquad.domain.Cart;
import codesquad.dto.SelectedItemDTO;
import codesquad.support.test.AcceptanceTest;
import codesquad.support.test.RequestEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiCartControllerAcceptanceTest extends AcceptanceTest {
    
    private static final Logger log = LoggerFactory.getLogger(ApiCartControllerAcceptanceTest.class);

    @Test
    public void saveCartTest() {
        SelectedItemDTO selectedItemDTO = new SelectedItemDTO(1L, 3L);
        RequestEntity requestEntity = new RequestEntity.Builder()
                .withUrl("/carts")
                .withBody(selectedItemDTO)
                .withMethod(HttpMethod.POST)
                .withReturnType(Cart.class)
                .build();

        ResponseEntity<Cart> responseEntity = request(template(), requestEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getSelectedItems().size()).isEqualTo(1);
        log.debug("cart : {}",responseEntity.getBody());

    }

}
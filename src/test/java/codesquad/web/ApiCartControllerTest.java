package codesquad.web;

import codesquad.dto.CartItem;
import codesquad.support.test.AcceptanceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCartControllerTest extends AcceptanceTest {

    @Test
    public void holdItemToCart() {

        CartItem cartItem = CartItem
                .builder()
                .id(Long.valueOf(100))
                .description("맛좋은 라면")
                .imgUrl("/img.png")
                .price(Long.valueOf(10000))
                .salesRate(Long.valueOf(20))
                .title("좋은라면")
                .amount(Long.valueOf(10))
                .build();

        ResponseEntity<Void> response = template().postForEntity("/cart", cartItem, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        log.debug("response.headers : {}", response.getHeaders());
    }

}

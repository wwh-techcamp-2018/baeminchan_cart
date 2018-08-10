package codesquad.web;

import codesquad.domain.Cart;
import codesquad.support.test.AcceptanceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCartAcceptanceTest extends AcceptanceTest {
    @Test
    public void save(){
        Cart cart = Cart.builder()
                .title("집밥 김치찌개")
                .description("맛있음")
                .imgUrl("www.naver.com")
                .price(10000)
                .build();
        ResponseEntity<Void> response = template().postForEntity("/products/cart", cart, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void count(){
        ResponseEntity<Integer> response = template().getForEntity("/products/cart/count", Integer.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}

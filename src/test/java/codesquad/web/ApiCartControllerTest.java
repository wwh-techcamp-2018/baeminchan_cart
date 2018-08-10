package codesquad.web;

import codesquad.domain.Cart;
import codesquad.dto.CartDTO;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCartControllerTest extends AcceptanceTest {

    @Test
    public void get() {
        ResponseEntity<CartDTO> response =  template().getForEntity("/api/carts", CartDTO.class);
        CartDTO testCartDto = new CartDTO(Cart.CART1);
        assertThat(response.getBody()).isEqualTo(testCartDto);
    }


}

package codesquad.web;

import codesquad.domain.CartRepository;
import codesquad.domain.Product;
import codesquad.domain.ResponseModel;
import codesquad.dto.CartDto;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CartControllerAcceptanceTest extends AcceptanceTest {
    private static final String CART_URL = "/api/cart";

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TestRestTemplate template;

    private Product product;
    private CartDto dto;
    private HttpHeaders headers;
    private HttpEntity httpEntity;
    private ParameterizedTypeReference<ResponseModel<Integer>> reference;


    @Before
    public void setUp() throws Exception {
        product = Product.builder().id(1L).build();
        dto = CartDto.builder()
                .productId("" + product.getId())
                .productNum(1)
                .build();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        httpEntity = new HttpEntity<>(dto, headers);
        reference = new ParameterizedTypeReference<ResponseModel<Integer>>() {
        };
    }

    @Test
    public void add_product_not_login_user() {
        // When
        ResponseEntity<ResponseModel<Integer>> response = template.exchange(CART_URL, HttpMethod.POST, httpEntity, reference);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualTo(1);
    }

    @Test
    public void add_product_login_user() {
        // When
        ResponseEntity<ResponseModel<Integer>> response = template.withBasicAuth("javajigi@woowahan.com", "password")
                .exchange(CART_URL,
                        HttpMethod.POST,
                        httpEntity,
                        reference);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualTo(1);
    }
}

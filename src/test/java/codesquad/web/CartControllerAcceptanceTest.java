package codesquad.web;

import codesquad.domain.*;
import codesquad.dto.ProductBundleDto;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class CartControllerAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(CartControllerAcceptanceTest.class);

    private static final String CART_URL = "/cart/products";

    private ProductBundleDto dto;

    private ProductBundleDto updateDto;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductBundleRepository productBundleRepository;

    @Before
    public void setUp() throws Exception {
        dto = ProductBundleDto.builder()
                .count(4L)
                .build();

        updateDto = ProductBundleDto.builder()
                .count(6L)
                .build();
    }

    @Test
    public void saveProductBundle() {
        String url = CART_URL + "/2";
        ResponseEntity<ProductBundle> entity = basicAuthTemplate().postForEntity(url, dto, ProductBundle.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(productBundleRepository.findAllByUser(User.defaultUser)).contains(entity.getBody());

        entity = basicAuthTemplate().postForEntity(url, updateDto, ProductBundle.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ProductBundle bundle = entity.getBody();
        assertThat(bundle.getCount()).isEqualTo(updateDto.getCount());
    }

    @Test
    public void removeProductBundle() {
        ProductBundle productBundle = productBundleRepository.findById(1L).get();
        ResponseEntity<ProductBundle> entity = basicAuthTemplate().exchange(
                CART_URL + "/1",
                HttpMethod.DELETE,
                null,
                ProductBundle.class
        );

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo(productBundle);
    }
}

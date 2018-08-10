package codesquad.web;

import codesquad.domain.*;
import codesquad.dto.ProductBundleInputDto;
import codesquad.dto.ProductBundleOutputDto;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CartControllerAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(CartControllerAcceptanceTest.class);

    private static final String CART_URL = "/cart/products";

    private ProductBundleInputDto dto;

    private ProductBundleInputDto updateDto;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductBundleRepository productBundleRepository;

    @Before
    public void setUp() throws Exception {
        dto = ProductBundleInputDto.builder()
                .count(4L)
                .build();

        updateDto = ProductBundleInputDto.builder()
                .count(6L)
                .build();
    }

    @Test
    public void saveProductBundle() {
        String url = CART_URL + "/2";
        ResponseEntity<ProductBundleOutputDto> entity = basicAuthTemplate().postForEntity(url, dto, ProductBundleOutputDto.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(productBundleRepository.findAllByUser(User.defaultUser)).contains(entity.getBody().getRecentBundle());

        entity = basicAuthTemplate().postForEntity(url, updateDto, ProductBundleOutputDto.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ProductBundleOutputDto bundleOutputDto = entity.getBody();
        assertThat(bundleOutputDto.getRecentBundle().getCount()).isEqualTo(updateDto.getCount() + dto.getCount());
    }

    @Test
    public void resetProductBundle() {
        ProductBundle productBundle = productBundleRepository.findById(1L).get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ProductBundleOutputDto> entity = basicAuthTemplate().exchange(
                CART_URL + "/" + productBundle.getId(),
                HttpMethod.PUT,
                new HttpEntity<ProductBundleInputDto>(updateDto, headers),
                ProductBundleOutputDto.class
        );

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getRecentBundle()).isEqualTo(productBundle);
    }

    @Test
    public void removeProductBundle() {
        ProductBundle productBundle = productBundleRepository.findById(1L).get();
        ResponseEntity<ProductBundleOutputDto> entity = basicAuthTemplate().exchange(
                CART_URL + "/1",
                HttpMethod.DELETE,
                null,
                ProductBundleOutputDto.class
        );

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getRecentBundle()).isEqualTo(productBundle);
    }
}

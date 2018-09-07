package codesquad.web;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BaeminchanControllerAcceptanceTest extends AcceptanceTest {
    private Product product;
    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        productRepository.deleteAll();
        product = productRepository.save(Product.builder().title("1").build());
    }

    @Test
    public void cart() {
        ResponseEntity<String> response = template().getForEntity("/cart", String.class);

        assertThat(response.getBody()).contains("장바구니");
    }

    @Test
    public void products() {
        productRepository.save(Product.builder().title("2").build());
        productRepository.save(Product.builder().title("3").build());
        ResponseEntity<String> response = template().getForEntity("/products", String.class);

        assertThat(response.getBody()).contains("밑반찬");
        assertThat(response.getBody()).contains(productRepository.findAll().stream()
                .map((e) -> e.getTitle()).collect(Collectors.toList()));
    }

    @Test
    public void product() {
        ResponseEntity<String> response = template().getForEntity(String.format("/product/%d", product.getId()), String.class);

        assertThat(response.getBody()).contains(product.getTitle());
    }
}

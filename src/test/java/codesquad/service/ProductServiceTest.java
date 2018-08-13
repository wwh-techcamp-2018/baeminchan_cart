package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void findAllByIds() {
        Product one = Product.builder()
                .id(1)
                .title("title")
                .description("description")
                .discountRate(10D)
                .imgUrl("")
                .price(1000L)
                .build();

        Product two = Product.builder()
                .id(2)
                .title("title")
                .description("description")
                .discountRate(10D)
                .imgUrl("")
                .price(1000L)
                .build();

        Product three = Product.builder()
                .id(3)
                .title("title")
                .description("description")
                .discountRate(10D)
                .imgUrl("")
                .price(1000L)
                .build();


        when(productRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(one, two));
        assertThat(productService.findAll(Arrays.asList(1L, 2L)))
                .containsExactly(one, two)
                .doesNotContain(three);
    }
}

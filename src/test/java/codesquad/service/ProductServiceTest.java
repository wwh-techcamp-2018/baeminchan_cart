package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    @InjectMocks
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productRepository.deleteAll();
    }

    @Test
    public void get_product() {
        Product product = Product.builder().id(1L).build();
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        assertThat(productService.findById(product.getId())).isEqualTo(product);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_wrong_product() {
        productService.findById(1L);
    }
}

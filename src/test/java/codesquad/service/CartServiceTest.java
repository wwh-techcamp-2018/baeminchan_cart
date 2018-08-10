package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private MockHttpSession session;

    private Product product;

    @Before
    public void setUp() throws Exception {
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product(1L, "title", "description", "url", 1000L, 10D)));
        product = productRepository.findById(1L).get();
        session = new MockHttpSession();
    }

    @Test
    public void addProductSuccess() {
        cartService.addProduct(session, 1L, 1);
        assertThat(cartService.getProducts(session)).containsKey(1L).containsValue(1);
    }

    @Test
    public void deleteProductSuccess() {
        cartService.addProduct(session, 1L, 1);
        cartService.deleteProduct(session, product.getId(), 1);
        assertThat(cartService.getProducts(session)).doesNotContainKey(1L);
    }
}

package codesquad.web.api;

import codesquad.domain.CartProduct;
import codesquad.domain.CartProductRepository;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.dto.CartProductDTO;
import codesquad.dto.ProductDTO;
import codesquad.service.ProductService;
import codesquad.support.test.AcceptanceTest;
import codesquad.utils.CartUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCartItemControllerTest extends AcceptanceTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartItemRepository;

    @Mock
    private ProductService productService;
    @InjectMocks
    private ApiCartController apiCartController;
    private Product savedProduct;
    private ProductDTO newProduct;

    @Before
    public void setUp() throws Exception {
        newProduct = new ProductDTO();
        savedProduct = productRepository.findById(1L).get();
    }

    @Test
    public void 비로그인_사용자_장바구니_품목_담기() {
        MockHttpSession session = new MockHttpSession();

        newProduct.setProductId(1);
        newProduct.setCount(new BigInteger("2"));

        apiCartController.create(newProduct, session);

        assertThat(((List<CartProductDTO>) session.getAttribute(CartUtils.CART_SESSION_KEY)).size()).isEqualTo(1);
        assertThat((((List<CartProductDTO>) session.getAttribute(CartUtils.CART_SESSION_KEY)).get(0).getCount())).isEqualTo(newProduct.getCount());
    }

    @Test
    public void 로그인_사용자_장바구니_품목_담기() {
        newProduct.setProductId(1);
        newProduct.setCount(new BigInteger("2"));

        ResponseEntity<ProductDTO> response = basicAuthTemplate().postForEntity("/api/carts", newProduct, ProductDTO.class);
        CartProduct cartItem = cartItemRepository.findById(1L).get();

        assertThat(response.getBody().getProductId()).isEqualTo(cartItem.getProduct().getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
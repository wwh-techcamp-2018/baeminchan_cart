package codesquad.web;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.dto.CartProductDTO;
import codesquad.dto.RequestCartProductDTO;
import codesquad.service.ProductService;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BaeminchanControllerTest extends AcceptanceTest {
    @Autowired
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;
    @InjectMocks
    private BaeminchanController baeminchanController;

    private Product savedProduct;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private List<CartProductDTO> productDTOs;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        productDTOs = new ArrayList<>();
        savedProduct = productRepository.findById(1L).get();
    }

    @Test
    public void 장바구니_품목_담기() {
        RequestCartProductDTO requestCartProductDTO = new RequestCartProductDTO();
        requestCartProductDTO.setProductId(1);
        requestCartProductDTO.setCount(2);
        ResponseEntity<CartProductDTO> response = template().postForEntity("/cart", requestCartProductDTO, CartProductDTO.class);
        assertThat(response.getBody().getId()).isEqualTo(savedProduct.getId());
        assertThat(response.getBody().getTitle()).isEqualTo(savedProduct.getTitle());
        assertThat(response.getBody().getCount()).isEqualTo(2);
    }

    @Test
    public void 이미_장바구니_품목_들어있는경우_담기() {
        // 장바구니에 품목 담기
        productDTOs.add(new CartProductDTO().setId(1L).setCount(2L));
        productDTOs.add(new CartProductDTO().setId(2L).setCount(4L));

        MockHttpSession httpSession = new MockHttpSession();
        httpSession.setAttribute("cartProductList", productDTOs);

        RequestCartProductDTO requestCartProduct = new RequestCartProductDTO().setProductId(1L).setCount(2L);
        baeminchanController.productsCart(requestCartProduct, httpSession);

        assertThat((List<CartProductDTO>) httpSession.getAttribute("cartProductList")).contains(new CartProductDTO().setId(2L).setCount(4L));
    }
}

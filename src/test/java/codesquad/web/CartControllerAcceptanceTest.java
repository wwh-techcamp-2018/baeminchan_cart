package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.CartItem;
import codesquad.domain.CartRepository;
import codesquad.domain.Product;
import codesquad.dto.CartProductDto;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CartControllerAcceptanceTest extends AcceptanceTest {

    @Autowired
    private CartRepository cartRepository;

    private Product product;
    private CartProductDto cartProductDto;
    private Cart savedCart;

    @Before
    public void setUp() throws Exception {
        cartRepository.deleteAll();

        product = new Product(1L, "product", "description", "dummy", 10000L);
        cartProductDto = new CartProductDto(product, 5);

        savedCart = cartRepository.save(Cart.createOf(defaultUser(), cartProductDto.toEntity()));
    }

    @Test
    public void storeProductInCart() {
        ResponseEntity<Void> responseEntity = basicAuthTemplate().postForEntity(
                "/carts/products",
                new HttpEntity<>(cartProductDto, getHeaders()),
                Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void getProductListInCart() {
        ResponseEntity<List<CartItem>> responseEntity =
                template().exchange(
                        "/carts",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<CartItem>>() {
                        }
                );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(savedCart.getCartItems());
    }
}

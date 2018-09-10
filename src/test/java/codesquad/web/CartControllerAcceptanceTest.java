package codesquad.web;


import codesquad.common.CartValue;
import codesquad.common.ResponseModel;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.dto.CartPriceDTO;
import codesquad.dto.CartProductDTO;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartControllerAcceptanceTest extends AcceptanceTest {
    private static final String CART_URL = "/api/cart";

    private Product productOne;
    private Integer productOneNum;
    private String cookie;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        productOne = productRepository.save(Product.builder()
                .title("집밥")
                .price(10_000L)
                .imgUrl("/static/img/productOne.png")
                .build());
        productOneNum = 3;
    }

    @Test
    public void count_empty_cart() {
        assertCartProductsNumber(requestCartProductsCount(), 0);
    }

    @Test
    public void count_cart_products() {
        addProduct(productOne, productOneNum);

        assertCartProductsNumber(requestCartProductsCount(), productOneNum);
    }

    @Test
    public void count_cart_multiple_products() {
        Product productTwo = productRepository.save(Product.builder().build());
        Integer productTwoNum = 4;
        addProduct(productOne, productOneNum);
        addProduct(productTwo, productTwoNum);

        assertCartProductsNumber(requestCartProductsCount(), productOneNum + productTwoNum);
    }

    @Test
    public void update_product() {
        productOneNum = 10;

        ResponseEntity<ResponseModel<Integer>> response = requestJson(
                String.format(CART_URL.concat("/products/%d?num=%d"), productOne.getId(), productOneNum),
                HttpMethod.PUT, null, new ParameterizedTypeReference<ResponseModel<Integer>>() {
                }, cookie);

        assertCartProductsNumber(response, productOneNum);
    }

    @Test
    public void get_product_detail() {
        addProduct(productOne, productOneNum);

        ResponseEntity<ResponseModel<CartProductDTO>> response = requestJson(String.format(CART_URL.concat("/products/%d"), productOne.getId()),
                HttpMethod.GET, null, new ParameterizedTypeReference<ResponseModel<CartProductDTO>>() {
                }, cookie);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData())
                .isEqualToComparingOnlyGivenFields(productOne, "title", "price", "imgUrl");
        assertThat(response.getBody().getData().getProductNum()).isEqualTo(productOneNum);
    }

    @Test
    public void get_product_list_details() {
        Product productTwo = productRepository.save(Product.builder()
                .title("혼밥")
                .price(12_000L)
                .imgUrl("/static/img/productTwo.png")
                .build());
        Integer productTwoNum = 4;
        addProduct(productOne, productOneNum);
        addProduct(productTwo, productTwoNum);

        ResponseEntity<ResponseModel<List<CartProductDTO>>> response = requestJson(CART_URL.concat("/products"),
                HttpMethod.GET, null, new ParameterizedTypeReference<ResponseModel<List<CartProductDTO>>>() {
                }, cookie);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).hasSize(2);
    }

    @Test
    public void get_total_price() {
        addProduct(productOne, productOneNum);

        ResponseEntity<ResponseModel<CartPriceDTO>> response = requestJson(CART_URL.concat("/price"),
                HttpMethod.GET, null, new ParameterizedTypeReference<ResponseModel<CartPriceDTO>>() {
                }, cookie);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getTotalProductPrice()).isEqualTo(productOne.getPrice() * productOneNum);
        assertThat(response.getBody().getData().getDeliveryCharge()).isEqualTo(CartValue.DELIVERY_CHARGE);
    }

    @Test
    public void delete_product() {
        addProduct(productOne, productOneNum);

        ResponseEntity<Void> response = requestJson(String.format(CART_URL.concat("/products/%d"), productOne.getId()),
                HttpMethod.DELETE, null, cookie);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private ResponseEntity<ResponseModel<Integer>> requestCartProductsCount() {
        return requestJson(CART_URL.concat("/products/count"),
                HttpMethod.GET, null, new ParameterizedTypeReference<ResponseModel<Integer>>() {
                }, cookie);
    }

    private void addProduct(Product product, Integer productNum) {
        ResponseEntity<ResponseModel<Integer>> response = requestJson(
                String.format(CART_URL.concat("/products/%d?add=%d"), product.getId(), productNum),
                HttpMethod.POST, null, new ParameterizedTypeReference<ResponseModel<Integer>>() {
                }, cookie);

        assertCartProductsNumber(response, productNum);

        if (cookie == null) {
            cookie = response.getHeaders().getFirst(response.getHeaders().SET_COOKIE);
        }
    }

    @Test
    public void get_wrong_product() {
        ResponseEntity<ResponseModel<CartProductDTO>> response = requestJson(String.format(CART_URL.concat("/products/%d"), 100L),
                HttpMethod.GET, null, new ParameterizedTypeReference<ResponseModel<CartProductDTO>>() {
                }, cookie);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private void assertCartProductsNumber(ResponseEntity<ResponseModel<Integer>> response, Integer number) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).isEqualTo(number);
    }
}

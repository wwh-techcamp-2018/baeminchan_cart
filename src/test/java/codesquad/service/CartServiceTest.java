package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartProductDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    Logger log = LoggerFactory.getLogger(CartServiceTest.class);

    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private CartProductService cartService;

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    CartProductDTO dto_product1, dto_product2;
    Product product1, product2;
    @Before
    public void setUp() throws Exception {
        product1 = Product.builder().id(1L).title("TEST Product1").price(1000L).discountRate(0L).build();
        product2 = Product.builder().id(2L).title("TEST Product2").price(2000L).discountRate(10L).build();
        // NOT WORKING
        // when(productService.findById(product1.getId())).thenReturn(product1 );
        // when(productService.findById(product2.getId())).thenReturn(product2 );

        //when(cartService.findProductByDTO(dto_product1)).thenReturn(product1);
        //when(cartService.findProductByDTO(dto_product2)).thenReturn(product1);

        dto_product1 = CartProductDTO.builder().count(3).productId(product1.getId()).build();
        dto_product2 = CartProductDTO.builder().count(6).productId(product2.getId()).build();

    }

    @Test
    public void addCartProduct_비회원() {
        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));
        when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));
//        Cart mockCart = mock(Cart.class);
//        when(cartRepository.save(Cart.EMPTY_CART)).thenReturn(mockCart);

        SoftAssertions softly = new SoftAssertions();
        Cart cart = cartService.initCartProduct(dto_product1, Cart.EMPTY_CART, User.GUEST_USER);
        log.debug("cart {}", cart);
        //softly.assertThat(cart.isEmptyCart()).isFalse().as("NOT EMPTY CART");
        softly.assertThat(cart.getCartProductCnt()).isEqualTo(1).as("PRODUCT CNT 1");

        List<CartProduct> cartProductStream = extractCartProduct(cart.getCartProducts(), dto_product1.getProductId());
        softly.assertThat(cartProductStream.size()).isEqualTo(1).as("CartProduct 중복 없음");
        softly.assertThat(cartProductStream.get(0).getCount()).isEqualTo(dto_product1.getCount()).as("CartProduct 수량 체크");

        dto_product1.setCount(5);

        cart = cartService.initCartProduct(dto_product1, cart, User.GUEST_USER);
        //softly.assertThat(cart.isEmptyCart()).isFalse().as("NOT EMPTY CART");
        softly.assertThat(cart.getCartProductCnt()).isEqualTo(1).as("PRODUCT CNT 1 - 증가 하지 않음");
        cartProductStream = extractCartProduct(cart.getCartProducts(), dto_product1.getProductId());

        softly.assertThat(cartProductStream.size()).isEqualTo(1).as("CartProduct 중복 없음");
        softly.assertThat(cartProductStream.get(0).getCount()).isEqualTo(dto_product1.getCount()).as("CartProduct 수량 변화 체크");

        cart = cartService.initCartProduct(dto_product2, cart, User.GUEST_USER);
        //softly.assertThat(cart.isEmptyCart()).isFalse().as("NOT EMPTY CART");
        softly.assertThat(cart.getCartProductCnt()).isEqualTo(2).as("PRODUCT CNT 2 - 증가");
        softly.assertThat(cart.getCartProducts().stream().map(x -> x.getProduct()).collect(Collectors.toList())).containsExactly(product1, product2).as("PRODUCT 순서 보장");
        cartProductStream = extractCartProduct(cart.getCartProducts(), dto_product2.getProductId());
        softly.assertThat(cartProductStream.get(0).getCount()).isEqualTo(dto_product2.getCount()).as("CartProduct 2 수량 체크");


        softly.assertAll();
    }

    public void addCartProduct_비회원_로그인() {
   
    }
    public List<CartProduct> extractCartProduct(Collection<CartProduct> cartProducts, Long productId){
        return cartProducts.stream().filter(x -> x.getProduct().getId().equals(productId)).collect(Collectors.toList());
    }
    @Test
    public void streamTest() {

        log.debug("arrays {}",
                Arrays.asList(CartProduct.builder().count(1).build()).stream().map(x -> x.getId()).collect(Collectors.toList()));
    }
}

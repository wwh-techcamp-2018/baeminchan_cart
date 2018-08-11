package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private CartService cartService;

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    CartProductDTO dto_product1, dto_product2;
    Product product1, product2;
    @Before
    public void setUp() throws Exception {
        product1 = Product.builder().id(1L).title("TEST Product1").price(1000L).build();
        product2 = Product.builder().id(2L).title("TEST Product2").price(2000L).build();
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
//        when(productService.findById(product1.getId())).thenReturn(product1 );
//        when(productService.findById(product2.getId())).thenReturn(product2 );
//        when(cartService.findProductByDTO(dto_product1)).thenReturn(product1);
//        when(cartService.findProductByDTO(dto_product2)).thenReturn(product2);

        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));
        when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));


        SoftAssertions softly = new SoftAssertions();
        Cart cart = cartService.addToCart(dto_product1, Cart.EMPTY_CART, User.GUEST_USER);
        log.debug("cart {}", cart);
        softly.assertThat(cart.isEmptyCart()).isFalse().as("NOT EMPTY CART");
        softly.assertThat(cart.getCartProductCnt()).isEqualTo(1).as("PRODUCT CNT 1");

        Stream<CartProduct> cartProductStream = extractCartProduct(cart.getCartProducts(), dto_product1.getProductId());
        softly.assertThat(cartProductStream.count()).isEqualTo(1).isEqualTo("CartProduct 중복 없음");
        softly.assertThat(cartProductStream.findFirst().get().getCount()).isEqualTo(dto_product1.getCount()).as("CartProduct 수량 체크");

        dto_product1.setCount(5);

        cart = cartService.addToCart(dto_product1, cart, User.GUEST_USER);
        softly.assertThat(cart.isEmptyCart()).isFalse().as("NOT EMPTY CART");
        softly.assertThat(cart.getCartProductCnt()).isEqualTo(1).as("PRODUCT CNT 1 - 증가 하지 않음");
        Stream<CartProduct> cartProductStream2 = extractCartProduct(cart.getCartProducts(), dto_product1.getProductId());

        softly.assertThat(cartProductStream2.count()).isEqualTo(1).isEqualTo("CartProduct 중복 없음");
        softly.assertThat(cartProductStream2.findFirst().get().getCount()).isEqualTo(dto_product1.getCount()).as("CartProduct 수량 변화 체크");

        cart = cartService.addToCart(dto_product2, cart, User.GUEST_USER);
        softly.assertThat(cart.isEmptyCart()).isFalse().as("NOT EMPTY CART");
        softly.assertThat(cart.getCartProductCnt()).isEqualTo(2).as("PRODUCT CNT 2 - 증가");
        Stream<CartProduct> cartProductStream3 = extractCartProduct(cart.getCartProducts(), dto_product2.getProductId());

        softly.assertThat(cartProductStream3.findFirst().get().getCount()).isEqualTo(dto_product2.getCount()).as("CartProduct 2 수량 체크");

    }
    public Stream<CartProduct> extractCartProduct(Set<CartProduct> cartProducts, Long productId){
        return cartProducts.stream().filter(x -> x.getProduct().getId().equals(productId));
    }
    @Test
    public void streamTest() {

        log.debug("arrays {}",
                Arrays.asList(CartProduct.builder().count(1).build()).stream().map(x -> x.getId()).collect(Collectors.toList()));
    }
}

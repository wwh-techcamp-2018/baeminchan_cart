package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartProductDto;
import codesquad.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private Product.ProductBuilder productBuilder;
    private User.UserBuilder userBuilder;
    private Cart.CartBuilder cartBuilder;

    @Before
    public void setUp() throws Exception {
        userBuilder = User.builder();
        productBuilder = Product.builder().id(1L);
        cartBuilder = Cart.builder().id(1L);
    }

    @Test
    public void add() {
        Product product = productBuilder.build();
        Cart cart = cartBuilder.build();

        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        assertThat(cartService.add(cart.getId(), product.getId()).getProducts().keySet()).contains(product.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void add_wrong_product() {
        Product product = productBuilder
                .id(Long.MAX_VALUE)
                .build();
        Cart cart = cartBuilder.build();

        when(productRepository.existsById(product.getId())).thenReturn(false);
        cartService.add(cart.getId(), product.getId());
    }

    @Test
    public void find_by_id() {
        Cart cart = cartBuilder.build();

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        assertThat(cartService.findById(cart.getId())).isEqualTo(cart);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void not_find_by_id() {
        Cart cart = cartBuilder.build();

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.empty());
        cartService.findById(cart.getId());
    }

    @Test
    public void get_cart_products() {
        Product product = productBuilder.price(10_000L).build();
        Product otherProduct = productBuilder.id(2L).price(12_000L).build();
        Cart cart = cartBuilder.build();

        cart.addProduct(product.getId(), 2);
        cart.addProduct(otherProduct.getId(), 1);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.findById(otherProduct.getId())).thenReturn(Optional.of(otherProduct));

        assertThat(cartService.getCartProducts(cart).get(0).getProductNum()).isEqualTo(2);
        assertThat(cartService.getCartProducts(cart).get(1).getProductNum()).isEqualTo(1);
    }

    @Test
    public void compute_cart_total_price() {
        List<CartProductDto> cartProductDtos = Arrays.asList(
                CartProductDto.builder().totalPrice(2_000L).build(),
                CartProductDto.builder().totalPrice(5_000L).build());

        assertThat(cartService.computeCartTotalPrice(cartProductDtos)).isEqualTo(7_000L);
    }

    @Test
    public void have_delivery_price() {
        assertThat(cartService.getDeliveryPrice(39_999L)).isEqualTo(2_500L);
    }

    @Test
    public void no_delivery_price() {
        assertThat(cartService.getDeliveryPrice(40_000L)).isEqualTo(0L);
    }
}

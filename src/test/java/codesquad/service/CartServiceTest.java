package codesquad.service;

import codesquad.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private User user;
    private Product product;
    private Product otherProduct;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        user = User.builder().id(1L).build();
        product = Product.builder()
                .id(1L)
                .title("곱창")
                .price(10_000L)
                .build();
        otherProduct = Product.builder()
                .id(2L)
                .title("간장게장")
                .price(9_000L)
                .build();
        cart = Cart.builder().id(1L).build();
    }

    @Test
    public void create() {
        // When
        when(cartRepository.save(any())).then(returnsFirstArg());
        Cart cart = cartService.create(user);

        // Then
        assertThat(cart.getUser()).isEqualTo(user);
    }

    @Test
    public void updateProductInCart() {
        // When
        when(cartRepository.save(any())).then(returnsFirstArg());
        Cart updatedCart = cartService.updateProductInCart(cart, product, 1);

        // Then
        assertThat(updatedCart.getProducts().keySet()).containsExactly(product.getId());
        assertThat(updatedCart.getProducts().get(product.getId())).isEqualTo(1);
    }

    @Test
    public void getProducts() {
        cart.updateProductNum(product.getId(), 3);
        cart.updateProductNum(otherProduct.getId(), 2);

        // When
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.findById(otherProduct.getId())).thenReturn(Optional.of(otherProduct));
        List<Product> products = cartService.getProducts(cart);

        // Then
        assertThat(products).containsExactly(product, otherProduct);

    }

    @After
    public void tearDown() throws Exception {
        productRepository.deleteAll();
        cartRepository.deleteAll();
    }
}

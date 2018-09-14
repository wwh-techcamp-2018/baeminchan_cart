package codesquad.service;

import codesquad.common.Message;
import codesquad.domain.*;
import codesquad.exception.ResourceNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

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
        when(cartRepository.save(any())).then(returnsFirstArg());
        Cart cart = cartService.create(user);

        assertThat(cart.getUser()).isEqualTo(user);
    }

    @Test
    public void add_product_number() {
        cart.updateProductNum(product.getId(), 3);
        when(cartRepository.save(any())).then(returnsFirstArg());
        Cart updatedCart = cartService.addProduct(cart, product.getId(), 2);

        assertThat(updatedCart.getProducts().get(product.getId())).isEqualTo(5);
    }

    @Test
    public void update_product_number() {
        when(cartRepository.save(any())).then(returnsFirstArg());
        Cart updatedCart = cartService.updateProduct(cart, product.getId(), 1);

        assertThat(updatedCart.getProducts().keySet()).containsExactly(product.getId());
        assertThat(updatedCart.getProducts().get(product.getId())).isEqualTo(1);
    }

    @Test
    public void get_products() {
        cart.updateProductNum(product.getId(), 3);
        cart.updateProductNum(otherProduct.getId(), 2);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.findById(otherProduct.getId())).thenReturn(Optional.of(otherProduct));
        List<Product> products = cartService.getProducts(cart);

        assertThat(products).containsExactly(product, otherProduct);
    }

    @Test
    public void delete_product() {
        cart.updateProductNum(product.getId(), 3);

        cartService.deleteProduct(cart, product.getId());

        assertThat(cart.getProducts()).isEmpty();
        assertThat(cart.getIsDeleted().keySet()).contains(product.getId());
    }

    @Test
    public void delete_multiple_products() {
        cart.updateProductNum(product.getId(), 3);
        cart.updateProductNum(otherProduct.getId(), 2);

        cartService.deleteMultiProducts(cart,
                Arrays.asList(product.getId(), otherProduct.getId()).stream().map((id) -> String.valueOf(id)).collect(Collectors.toList()));

        assertThat(cart.getProducts()).isEmpty();
        assertThat(cart.getIsDeleted().keySet()).contains(product.getId(), otherProduct.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_wrong_product() {
        cart.updateProductNum(100L, 3);

        when(messageSourceAccessor.getMessage(anyString())).thenReturn(Message.NOT_EXIST_PRODUCT);

        cartService.getProducts(cart);
    }

    @After
    public void tearDown() throws Exception {
        productRepository.deleteAll();
        cartRepository.deleteAll();
    }
}

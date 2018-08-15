package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartDto;
import codesquad.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

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
        cartBuilder = Cart.builder();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void add_wrong_product() {
        Product product = productBuilder
                .id(Long.MAX_VALUE)
                .build();
        Cart cart = cartBuilder.build();
        CartDto dto = CartDto.builder().productId("" + product.getId()).build();

        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        cartService.add(cart, dto);
    }
}

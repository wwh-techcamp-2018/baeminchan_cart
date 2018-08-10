package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartItemDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartItemServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductService productService;
    @Mock
    private CartItemRepository cartRepository;
    @InjectMocks
    private CartItemService cartItemService;
    @Test
    public void createTest() {
        CartItem cartItem = new CartItem();
        User user = new User();
        Product product = new Product();
        CartItemDto cartItemDto = new CartItemDto();
        when(productService.findById(cartItemDto.getProductId())).thenReturn((product));
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItemService.save(user, cartItemDto);
        verify(cartRepository).save(cartItem);
    }


}
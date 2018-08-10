package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;
    private Cart updateCart;

    private Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product(20L,
                "[집밥의완성] 궁중식 소고기오이감정 370g",
                "여름 오이와 함께 되직하게 끓여낸 입맛을 사로잡는 궁중요리",
                "https://cdn.bmf.kr/_data/product/I4DEC/ebab7a5c6f31b59c1d0ffda25f0c82a3.jpg",
                5000L,
                10);
        cart = new Cart("[집밥의완성] 궁중식 소고기오이감정 370g", 5000L, 10);
        updateCart = new Cart("[집밥의완성] 궁중식 소고기오이감정 370g", 5000L, 5);
    }

    @Test
    public void save() {
        cartService.save(cart);
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    public void delete() {
        cartService.delete(cart);
        verify(cartRepository, times(1)).delete(any());
    }

    @Test
    public void update() {
        when(cartRepository.findById((long) 1)).thenReturn(Optional.ofNullable(cart));
        cartService.update(updateCart);
        verify(cartRepository, times(1)).save(any());
    }
}

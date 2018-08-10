package codesquad.service;


import codesquad.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;


    @Test
    public void putInCart() {
        Cart cart = new Cart();
        when(productRepository.findById(1l)).thenReturn(Optional.of(Product.defaultProduct()));
        when(productRepository.findById(2l)).thenReturn(Optional.of(Product.defaultProduct()));
        cartService.putInCart(cart, 1);
        cartService.putInCart(cart, 2);
        //TODO: need to very save
        
        assertThat(cart.getProductSets().size()).isEqualTo(2);
        assertThat(cart.getProductSets().get(0).getProduct().getTitle()).contains("냉호박죽");

    }

    @Test
    public void mergeCart() {

    }
}

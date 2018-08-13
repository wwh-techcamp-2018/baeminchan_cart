package codesquad;

import codesquad.domain.*;
import codesquad.dto.CartProductDTO;
import codesquad.support.PriceCalcultor;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;
    @Test
    public void testEquals(){
        Cart cart = new Cart();
        Product product = productRepository.findById(1L).get();
        CartProductDTO dto = CartProductDTO.builder().count(10).build();
        dto.fill(cart, product, new PriceCalcultor());
        CartProduct cartProduct = dto.toEntity();

        Cart returnedCart = cartRepository.save(cartProduct.getCart());

        //ADD new one
        dto.fill(returnedCart, product, new PriceCalcultor());

        CartProduct newCartProduct = dto.toEntity();

        Cart returnedCart2 = cartRepository.save(newCartProduct.getCart());
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(cart.equals(returnedCart)).isTrue();
        softly.assertThat(newCartProduct.equals(cartProduct)).isTrue();

        softly.assertThat(returnedCart.equals(returnedCart2)).isTrue();
        softly.assertThat(newCartProduct.equals(returnedCart.getCartProducts().get(0))).isTrue();
        softly.assertThat(newCartProduct.equals(returnedCart2.getCartProducts().get(0))).isTrue();
        softly.assertThat(returnedCart.getCartProducts().contains(newCartProduct));
        softly.assertThat(returnedCart2.getCartProducts().contains(newCartProduct));

        softly.assertAll();
    }
}

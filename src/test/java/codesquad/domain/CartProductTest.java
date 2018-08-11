package codesquad.domain;

import codesquad.dto.CartProductDTO;
import codesquad.support.PriceCalcultor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
public class CartProductTest {
    Product product;
    CartProduct cartProduct;
    Cart cart;
    PriceCalcultor priceCalcultor;

    @Before
    public void setUp(){
        SoftAssertions softly = new SoftAssertions();
        priceCalcultor = new PriceCalcultor();


    }
    private Long getDiscountPrice(){
        return priceCalcultor.calculatePrice(product.getPrice(), product.getDiscountRate(), cartProduct.getCount());
    }
    private Long getTotalDiscountPrice(){
        return priceCalcultor.calculateTotalPrice(getDiscountPrice());
    }

    @Test
    public void test_가격정보_Map반환확인_할인적용(){

        product = Product.builder().price(1000L).discountRate(PriceCalcultor.NO_DISCOUNT_THRESHOLD - 10L).build();

        cart = new Cart();
        CartProductDTO cartProductDTO = CartProductDTO.builder().count(PriceCalcultor.DISCOUNT_AMT_THRESHOLD + 40).build();
        cartProductDTO.fill(cart, product, priceCalcultor);
        cartProduct = cartProductDTO.toEntity();

        log.debug("cartProduct totalPrice {} ", cartProduct.getTotalPrice());

        cart.addCartProduct(cartProduct);
        Map<String, Long> calculation = cart.getPrice(priceCalcultor);


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(calculation.get("totalPrice")).isGreaterThanOrEqualTo(PriceCalcultor.DELIVERY_FEE_FREE_THRESHOLD).as("40000원 이상 구매 확이");
        //softly.assertThat(calculation).containsValues(getDiscountPrice(), getTotalDiscountPrice()).as("10 개 이상 주문, 20 미만 할인율, 40000원 이상 구매 배송비 무료 - 할인 적용");
        //softly.assertThat(calculation).containsValue(getDiscountPrice()).as("10 개 이상 주문, 20 미만 할인율, 40000원 이상 구매 배송비 무료 - 할인 적용");
        log.debug("1 {}",getDiscountPrice());
        softly.assertThat(calculation.get("totalPrice")).isEqualTo(getDiscountPrice());
        softly.assertThat(calculation.get("deliveryTotalPrice")).isEqualTo(getTotalDiscountPrice());

        softly.assertAll();
    }


                /*
                 같은 반찬을 10개이상 주문하면 기존 할인율에서 5% 추가 할인한다.
할인율이 20% 이상인 반찬은 10개 이상 주문하더라도 5% 추가 할인하지 않는다.

구매 금액이 4만원 미만인 경우 2500원의 배송비가 추가된다.
구매 금액이 4만원 이상 구매시 배송비가 무료이다.

                 */

}

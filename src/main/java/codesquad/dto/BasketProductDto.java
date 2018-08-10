package codesquad.dto;

import codesquad.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketProductDto {

    public static final double DELIVERY_PRICE_LIMIT = 40000;
    public static final double DELIVERY_PRICE = 2500;

    private Product product;
    private Long ea;
    private double price;


    public static BasketProductDto createBasketProductDto(Product product,Long ea){
        double price = checkDiscount(product, ea);
        log.info(price+"@@@@@@@@@@@@@@@@@@@@@@@@@"+product.getId());
       return new BasketProductDto(product,ea,price);
   }


    public static double checkDiscount(Product product, Long ea) {
        double price = product.getPrice() * ea;

        if (ea > 10) {
            price -= (price * 0.05);
        }

        if (price <DELIVERY_PRICE) {
            price +=DELIVERY_PRICE;
        }

        return price;
    }


}

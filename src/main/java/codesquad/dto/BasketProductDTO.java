package codesquad.dto;

import codesquad.domain.Product;
import codesquad.util.SaleUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketProductDTO {


    private Product product;
    private Long ea;
    private Long price;

    public static BasketProductDTO createBasketProductDto(Product product, Long ea){
        return new BasketProductDTO(product, ea, SaleUtils.checkDeliveryPrice(SaleUtils.checkDiscount(product, ea)));
   }

}

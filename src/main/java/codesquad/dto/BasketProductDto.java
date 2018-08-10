package codesquad.dto;

import codesquad.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketProductDto {
   private Product product;
   private Long ea;
   private double price;
   public static BasketProductDto createBasketProductDto(Product product,Long ea){
       double price=product.getPrice()*ea;
       if(ea>5) {
          price -= (price * 0.05);
       }
       return new BasketProductDto(product,ea,price);
   }


}

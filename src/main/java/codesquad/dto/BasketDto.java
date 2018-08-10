package codesquad.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class BasketDto {
    private Long id;
    private Long ea;

    public List<BasketDto> updateBasket(List<BasketDto> basketDtoList) {
        boolean check = false;
        for (BasketDto basket : basketDtoList) {
             check = checkBasket(basket);
        }
        if(!check){
            basketDtoList.add(this);
        }

        return basketDtoList;
    }

    public boolean checkBasket(BasketDto basketDto){
        if(basketDto.getId() == this.id){
            basketDto.setEa(basketDto.getEa() + this.ea);
            return true;
        }
        return false;
    }
}

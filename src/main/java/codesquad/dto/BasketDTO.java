package codesquad.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class BasketDTO {
    private Long id;
    private Long ea;

    public List<BasketDTO> updateBasket(List<BasketDTO> basketDtoList) {
        BasketDTO basketDto = basketDtoList.stream().filter(bDto -> bDto.getId() == id).findFirst().orElse(null);
        if (basketDto == null) {
            basketDtoList.add(this);
            return basketDtoList;
        }

        basketDto.setEa(basketDto.getEa() + ea);
        return basketDtoList;
    }
}

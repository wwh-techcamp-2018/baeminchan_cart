package codesquad.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private int amount;

    public CartDTO(Long id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

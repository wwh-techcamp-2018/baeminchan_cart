package codesquad.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class AddCartProductDTO {

    @NotNull
    @Min(1)
    private int count;

    @NotNull
    private Long productId;
}

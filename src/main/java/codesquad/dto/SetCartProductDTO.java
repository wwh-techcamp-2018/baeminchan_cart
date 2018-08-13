package codesquad.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class SetCartProductDTO {

    @NotNull
    @Min(1)
    private int count;

    @NotNull
    private Long cartId;

    @NotNull
    private Long productId;
}

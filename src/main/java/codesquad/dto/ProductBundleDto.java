package codesquad.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductBundleDto {

    @NotNull
    @Min(1)
    private Long count;
}

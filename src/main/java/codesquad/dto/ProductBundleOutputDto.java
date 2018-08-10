package codesquad.dto;

import codesquad.domain.ProductBundle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBundleOutputDto {

    private ProductBundle recentBundle;

    private int bundleCount;
}

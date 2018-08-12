package codesquad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long id;
    private String imgUrl;
    private String description;
    private String title;
    private Long price;
    private Long salesRate;
    private Long amount;
}

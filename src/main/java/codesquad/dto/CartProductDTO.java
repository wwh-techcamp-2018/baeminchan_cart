package codesquad.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

public class CartProductDTO {
    private Long id;
    private int amount;
    private boolean isDelivered;

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isDelivered() {
        return isDelivered;
    }
}

package codesquad.dto;

import lombok.Data;

@Data
public class RequestCartProductDTO {
    private long productId;
    private long count;

    public RequestCartProductDTO setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public RequestCartProductDTO setCount(long count) {
        this.count = count;
        return this;
    }
}

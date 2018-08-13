package codesquad.dto;

public class SelectedItemDTO {

    private Long productId;

    private Long count;

    public SelectedItemDTO() {
    }

    public SelectedItemDTO(Long productId, Long count) {
        this.productId = productId;
        this.count = count;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}

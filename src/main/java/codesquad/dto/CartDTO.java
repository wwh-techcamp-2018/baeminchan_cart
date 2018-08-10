package codesquad.dto;

import javax.validation.constraints.NotBlank;

public class CartDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private Long quantity;

    @NotBlank
    private Long saleRate;

    public CartDTO() {
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", saleRate=" + saleRate +
                '}';
    }

    public CartDTO(Long id, Long quantity, Long saleRate) {
        this.id = id;
        this.quantity = quantity;
        this.saleRate = saleRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Long saleRate) {
        this.saleRate = saleRate;
    }
}

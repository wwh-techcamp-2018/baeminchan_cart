package codesquad.dto;


import javax.validation.constraints.NotNull;

public class ProductDTO {

    @NotNull
    private Long id;

    @NotNull
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}

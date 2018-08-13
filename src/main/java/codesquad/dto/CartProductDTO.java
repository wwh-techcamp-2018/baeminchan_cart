package codesquad.dto;

import codesquad.domain.Product;

public class CartProductDTO {

    private Product product;

    private Integer count;

    public CartProductDTO() {

    }

    public CartProductDTO(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

}

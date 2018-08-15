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

    public Integer getSumPrice() {
        return product.getSalesPrice(count);
    }

    public Integer getCount() {
        return count;
    }

}

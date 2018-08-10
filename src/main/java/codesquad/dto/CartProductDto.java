package codesquad.dto;

import codesquad.domain.CartItem;
import codesquad.domain.Product;

public class CartProductDto {

    private Product product;
    private int productCount;

    public CartProductDto() {
    }

    public CartProductDto(Product product, int productCount) {
        this.product = product;
        this.productCount = productCount;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductCount() {
        return productCount;
    }

    public CartItem toEntity() {
        return new CartItem(product, productCount);
    }
}

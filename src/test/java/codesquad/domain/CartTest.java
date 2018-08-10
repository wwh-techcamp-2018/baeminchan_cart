package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class CartTest {

    private Cart cart;

    private ProductBundle productBundle;

    private ProductBundle sameProductBundle;

    private ProductBundle diffProductBundle;

    @Before
    public void setUp() throws Exception {
        cart = Cart.testCart();
        productBundle = ProductBundle.builder()
                .id(1)
                .product(new Product())
                .count(3)
                .build();

        sameProductBundle = productBundle;
        diffProductBundle = ProductBundle.builder()
                .id(2)
                .product(new Product())
                .count(4)
                .build();
    }

    @Test
    public void saveProductBundle() {
        long productCount = productBundle.getCount();
        cart.saveProductBundle(productBundle);
        assertThat(productBundle.getCount()).isEqualTo(productCount);
    }
    
    @Test
    public void saveProductBundleAlreadyExist() {
        cart.saveProductBundle(productBundle);
        cart.saveProductBundle(sameProductBundle);
        long sameProductCount = sameProductBundle.getCount();
        assertThat(productBundle.getCount()).isEqualTo(sameProductCount);
    }

    @Test
    public void removeProductBundle() {
        cart.saveProductBundle(productBundle);
        cart.saveProductBundle(sameProductBundle);
        cart.saveProductBundle(diffProductBundle);
        cart.removeProductBundle(sameProductBundle);
        assertThat(cart.getBundles().size()).isEqualTo(1);
    }
}
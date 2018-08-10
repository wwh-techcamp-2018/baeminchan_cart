package codesquad.domain;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

public class CartTest {
    @Resource
    private ProductRepository productRepository;
    private Product defaultProduct;

    @Before
    public void setUp() throws Exception {
        defaultProduct = getFirstProduct();
    }

    @Test
    public void addProduct() {

    }

    private Product getFirstProduct() {
        return productRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
    }
}

package codesquad.dto;

import codesquad.domain.Product;
import codesquad.service.ProductService;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class CartDTO {

    private LinkedHashMap<Product, Integer> products;

    public CartDTO(LinkedHashMap<Long, Integer> sessionCart, ProductService productService) {
        products = sessionCart.entrySet().stream()
                .collect(
                    Collectors.toMap(
                        entry -> productService.findById(entry.getKey()), entry -> entry.getValue(),
                        (u, v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); },
                        LinkedHashMap::new
                    )
                );
    }

    public LinkedHashMap<Product, Integer> getProducts() {
        return products;
    }

}

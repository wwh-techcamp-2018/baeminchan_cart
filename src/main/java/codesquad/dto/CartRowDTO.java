package codesquad.dto;

import codesquad.domain.Product;
import lombok.Getter;

import java.util.Map;

@Getter
public class CartRowDTO {

    private String title;
    private Long price;
    private Long count;
    private Long totalPrice;

    private CartRowDTO(String title, Long price, Long count, Long totalPrice) {
        this.title = title;
        this.price = price;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public static CartRowDTO create(Map.Entry<Product, Long> entry, Long count) {
        String title = entry.getKey().getTitle();
        Long price = entry.getKey().getPrice();
        Long totalPrice = entry.getValue();

        return new CartRowDTO(title, price, count, totalPrice);
    }
}

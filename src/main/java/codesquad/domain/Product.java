package codesquad.domain;

import codesquad.support.PriceCalcultor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Data
//todo Builder 삭제
@Builder @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 255)
    private String title;

    @Size(min = 1, max = 255)
    private String description;

    @Size(min = 1, max = 255)
    private String imgUrl;

    @DecimalMin(value = "0")
    private Long price;

    //todo categoryId
    @ManyToOne(optional = false)
    private Category category;

    //todo discountRate -- int? long?
    @Column(nullable = false)
    private Long discountRate = 0L;

    @Column(nullable = false)
    private boolean isDeliverable = false;

    //todo priceCalculator 구현
    public Long calculatePrice(PriceCalcultor priceCalcultor, int count) {
        return priceCalcultor.calculatePrice(price, discountRate, count);
    }
}

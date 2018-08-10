package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    public static final Product SALMON = new Product("연어", 10000L);
    public static final Product PIZZA = new Product("피자", 15500L);
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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Long getPrice() {
        return price;
    }

    public Product(@Size(min = 1, max = 255) String title, @DecimalMin(value = "0") Long price) {
        this.title = title;
        this.price = price;
    }
}

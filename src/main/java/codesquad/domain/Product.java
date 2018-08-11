package codesquad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
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

    private Double discountPercent;

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

    public boolean equalId(Long id) {
        return this.id.equals(id);
    }

    public double getDiscountPercent() {
        return 10.0;
    }

    public Product() {
    }

    public Product(Long price, Double discountPercent) {
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public long calculate(int count) {
        double price = this.price * (100 - discountPercent) / 100 * count;
        if (count >= 10 && discountPercent < 20.0) {
            return Math.round(price * 0.95);
        }
        return Math.round(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package codesquad.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@NoArgsConstructor
@ToString
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

    @DecimalMin(value = "0.0")
    private Double discountRate;

    public Product(String title, Long price, Double discountRate) {
        this.title = title;
        this.price = price;
        this.discountRate = discountRate;
    }

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

    public Double getDiscountRate() {
        return discountRate;
    }

    public long getSellingPrice() {
        return this.price - (long)(this.price * this.discountRate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(title, product.title) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imgUrl, product.imgUrl) &&
                Objects.equals(price, product.price) &&
                Objects.equals(discountRate, product.discountRate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, imgUrl, price, discountRate);
    }
}

package codesquad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Arrays;
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

    public static Product defaultProduct() {
        return new Product("냉호박죽", "맛있는...", "https://cdn.bmf.kr/_data/product/IC5BB/7db9fa5a56f6203836ae7c90ab67b3ce.jpg", 5000l);
    }

    public Product(String title, String description, String imgUrl, Long price) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public Product(Long id, @Size(min = 1, max = 255) String title, @Size(min = 1, max = 255) String description, @Size(min = 1, max = 255) String imgUrl, @DecimalMin(value = "0") Long price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public Product() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(title, product.title) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imgUrl, product.imgUrl) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, imgUrl, price);
    }
}

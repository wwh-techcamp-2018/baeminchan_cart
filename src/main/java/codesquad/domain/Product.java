package codesquad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

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

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double discountRate;

    public Product() {

    }

    public Product(Long id, @Size(min = 1, max = 255) String title, @Size(min = 1, max = 255) String description, @Size(min = 1, max = 255) String imgUrl, @DecimalMin(value = "0") Long price, @DecimalMin(value = "0") @DecimalMax(value = "100") Double discountRate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
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

    public Long getDiscountRate() {
        return Double.valueOf(discountRate).longValue();
    }

    public Long getSalesPrice() {
        return price - Double.valueOf((price * discountRate) / 100).longValue();
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Long id;
        private String title;
        private String description;
        private String imgUrl;
        private Long price;
        private Double discountRate;

        public ProductBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public ProductBuilder price(Long price) {
            this.price = price;
            return this;
        }

        public ProductBuilder discountRate(Double discountRate) {
            this.discountRate = discountRate;
            return this;
        }

        public Product build() {
            return new Product(id, title, description, imgUrl, price, discountRate);
        }
    }
}

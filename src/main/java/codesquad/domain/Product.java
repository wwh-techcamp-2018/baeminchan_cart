package codesquad.domain;

import codesquad.dto.CartProductDTO;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
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


    public Product(String title, Long price) {
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Product(@DecimalMin(value = "0") Long price) {
        this.price = price;
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

    public CartProductDTO toProductDTO(long count) {
        return new CartProductDTO(this.id, this.title, count, price);
    }

    public Double calculateSalePrice(long count, double discountRate) {
        if (count >= 10 && discountRate < 0.2) {
            discountRate += 0.05;
        }

        double salesPrice = (this.price * count) - ((this.price * count) * discountRate);

        if (salesPrice < 40000) {
            salesPrice += 2500;
        }
        return salesPrice;
    }
}

package codesquad.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Product {

    private static final int MAX_DISCOUNT_RATE = 20;
    private static final int DISCOUNT_RATE_FOR_AMOUNTS = 5;
    private static final int DISCOUNTABLE_AMOUNTS = 10;

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
    @Column(nullable = true)
    private int saleRate;

    public Product() {
    }

    public Product(@DecimalMin(value = "0") Long price, @DecimalMin(value = "0") int saleRate) {
        this.price = price;
        this.saleRate = saleRate;
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

    public int getSaleRate() {
        return saleRate;
    }

    public long calculatePrice(int amount){
        if(isMoreDisCountable() && amount >= DISCOUNTABLE_AMOUNTS)
            return price * amount * (100 - saleRate - DISCOUNT_RATE_FOR_AMOUNTS) / 100;
        return price * amount * (100 - saleRate) / 100;
    }

    private boolean isMoreDisCountable(){
        return saleRate + DISCOUNT_RATE_FOR_AMOUNTS <= MAX_DISCOUNT_RATE;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", price=" + price +
                '}';
    }
}

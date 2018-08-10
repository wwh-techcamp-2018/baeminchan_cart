package codesquad.domain;

import javax.persistence.*;
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
    private int price;

    private double discountRate;

    @Column(nullable = true)
    private int discountedPrice;

    @Column(nullable = true)
    private int count;

    @Column(nullable = true)
    private int totalPrice;

    public Product() {}

    public Product(String title, String description, String imgUrl, int price, int discountRate) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.discountRate = discountRate;
        this.discountedPrice = calculateDiscountedPrice();
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

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public int getCount() {
        return count;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    private int calculateDiscountedPrice() {
        return (int)(this.price * (1 - (discountRate * 0.01)));
    }

    public void calculatePrice(int count) {
        this.count = count;

        if(count < 10 && discountRate < 20) {
            discountRate += 5;
            discountedPrice = calculateDiscountedPrice();
        }

        this.totalPrice = discountedPrice * count;
    }
}

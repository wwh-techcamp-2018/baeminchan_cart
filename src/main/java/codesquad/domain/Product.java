package codesquad.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Getter
@ToString
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

    public Product(Long id, String title, String description, String imgUrl, Long price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

}

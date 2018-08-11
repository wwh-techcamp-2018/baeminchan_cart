package codesquad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(columnDefinition = "BIGINT default 0")
    private Long sale;


}

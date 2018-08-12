package codesquad.domain;

import codesquad.support.MoneyFormatter;
import codesquad.support.PriceCalcultor;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.text.NumberFormat;

@Entity
@Data
@Slf4j
//todo Builder 삭제
@Builder @AllArgsConstructor
//todo 왜 NoArgsConstructor 따로..?
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

    //todo categoryId
    @JsonIgnore @ToString.Exclude
    @ManyToOne(optional = false)
    private Category category;

    //todo discountRate -- int? long?
    @Column(nullable = false)
    private Long discountRate = 0L;

    @Column(nullable = false)
    private boolean isDeliverable = false;

    //todo priceCalculator 구현
    public Long calculatePrice(PriceCalcultor priceCalcultor, int count) {
        return priceCalcultor.calculatePrice(price, discountRate, count);
    }
    //todo 해결방법 찾기
    @JsonGetter("formattedPrice")
    @Autowired
    public String getFormattedPrice(MoneyFormatter moneyFormatter){
        log.debug("getFormattedPrice called");
        return moneyFormatter.longToMoney(this.price);
    }
    @JsonGetter("formattedPrice")
    @Autowired
    public String getFormattedPrice(){
        log.debug("getFormattedPrice called");
        return String.valueOf(this.price);
    }
}

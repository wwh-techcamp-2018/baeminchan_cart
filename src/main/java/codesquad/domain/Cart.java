package codesquad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;
import java.util.HashMap;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    private User user;

    @MapKeyJoinColumn
    @CollectionTable
    @Lob
    private HashMap<Long, Integer> products;

    @DecimalMin(value = "0")
    private Long deliveryCharge;

    @DateTimeFormat
    private Date createDate;

    @DateTimeFormat
    private Date updatedDate;

    @DateTimeFormat
    private Date orderDate;

    private boolean buy;

    public Cart() {
        this.products = new HashMap<>();
    }

    @Builder
    public Cart(Long id, User user, Long deliveryCharge, Date createDate, Date updatedDate, Date orderDate) {
        this();
        this.id = id;
        this.user = user;
        this.deliveryCharge = deliveryCharge;
        this.createDate = createDate;
        this.updatedDate = updatedDate;
        this.orderDate = orderDate;
    }

    public void addProduct(Long productId, Integer productNum) {
        if (!products.containsKey(productId)) {
            products.put(productId, 0);
        }
        products.put(productId, products.get(productId) + productNum);
    }

    public Integer getSumProductNum() {
        return products.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void setUserIfNot(User user) {
        if (!matchUser(user)) {
            this.user = user;
        }
    }

    private boolean matchUser(User user) {
        return this.user == user;
    }
}

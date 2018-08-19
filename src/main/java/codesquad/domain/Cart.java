package codesquad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    public Cart(Long id, User user, Date createDate, Date updatedDate, Date orderDate) {
        this();
        this.id = id;
        this.user = user;
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

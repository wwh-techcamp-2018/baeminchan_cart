package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JsonIgnore
    private User user;

    // Todo: Consider replacing productId to product
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

    private Long productsTotalPrice;

    private Long deliveryCharge;

    private boolean buy;

    @Builder
    public Cart(Long id, User user, Date createDate, Date updatedDate, Date orderDate, Long productsTotalPrice, Long deliveryCharge, boolean buy) {
        this.id = id;
        this.user = user;
        this.products = new HashMap<>();
        this.createDate = createDate;
        this.updatedDate = updatedDate;
        this.orderDate = orderDate;
        this.productsTotalPrice = productsTotalPrice;
        this.deliveryCharge = deliveryCharge;
        this.buy = buy;
    }

    public Cart(HashMap<Long, Integer> products) {
        this.products = products;
    }

    public void updateProductNum(Long productId, Integer productNum) {
        if (!products.containsKey(productId)) {
            products.put(productId, 0);
        }
        products.put(productId, productNum);
    }

    public Integer getSumProductNum() {
        return products.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void setUserIfNot(User user) {
        if (!matchUser(user)) {
            this.user = user;
        }
    }

    public void totalProductsPrice(List<Product> productList) {
        this.productsTotalPrice = productList.stream()
                .filter((prd) -> products.containsKey(prd.getId()))
                .mapToLong((prd) -> productsPrice(prd)).sum();
    }

    public Integer productNum(Long productId) {
        if (products.containsKey(productId)) {
            return products.get(productId);
        }
        return 0;
    }

    public List<Long> productsIdList() {
        return new ArrayList<>(products.keySet());
    }

    public void updateDeliveryCharge() {
        this.deliveryCharge = (this.productsTotalPrice >= CartStaticValue.DELIVERY_CHARGE_REFERENCE)
                ? 0
                : CartStaticValue.DELIVERY_CHARGE;
    }

    public boolean isEmpty() {
        return this.products.isEmpty();
    }

    private boolean matchUser(User user) {
        return this.user == user;
    }

    private Long productsPrice(Product product) {
        Integer productNum = products.get(product.getId());
        return (long) computeDiscountedPrice(product.getPrice(), productNum, product.getDiscountRatio()) * productNum;
    }

    private double computeDiscountedPrice(Long price, Integer number, double discountedRatio) {
        return (number >= CartStaticValue.FREE_DEIVERY_PRODUCT_NUM
                && discountedRatio < CartStaticValue.DISCOUNT_RATIO_LIMIT)
                ? price * (1 - discountedRatio - CartStaticValue.ADDITIONAL_DISCOUNT_RATIO)
                : price * (1 - discountedRatio);
    }
}

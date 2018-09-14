package codesquad.domain;

import codesquad.common.CartValue;
import codesquad.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
    @CollectionTable
    @ElementCollection
    private Map<Long, Integer> products = new LinkedHashMap<>();

    @CollectionTable
    @ElementCollection
    private Map<Long, Integer> isDeleted = new LinkedHashMap<>();

    @CreatedDate
    private Date createAt;

    @LastModifiedDate
    private Date updatedAt;

    private boolean buy = false;

    public Cart(LinkedHashMap<Long, Integer> products) {
        this.products = products;
    }

    @Builder
    public Cart(Long id, User user, Date createAt, Date updatedAt) {
        this.id = id;
        this.user = user;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public void updateProductNum(Long productId, Integer productNum) {
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

    public Long computeTotalProductsPrice(List<Product> productList) {
        if (productList.isEmpty()) {
            return 0L;
        }

        return productList.stream()
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

    public Long getDeliveryCharge(Long totalPrice) {
        return (totalPrice >= CartValue.DELIVERY_CHARGE_REFERENCE || totalPrice == 0)
                ? 0L
                : CartValue.DELIVERY_CHARGE;
    }

    public boolean isEmpty() {
        return this.products.isEmpty();
    }

    public void deleteProduct(Long productId) {
        if (!products.containsKey(productId)) {
            throw new ResourceNotFoundException("존재하지 않는 반찬입니다.");
        }

        isDeleted.put(productId, isDeleted.getOrDefault(productId, 0) + products.get(productId));

        products.remove(productId);
    }

    public void deleteMultiProducts(List<Long> productIds) {
        productIds.stream().forEach(id -> deleteProduct(id));
    }

    private boolean matchUser(User user) {
        return this.user == user;
    }

    private Long productsPrice(Product product) {
        Integer productNum = products.get(product.getId());
        return CartValue.getDiscountedPrice(product, productNum) * productNum;
    }

}

package codesquad.domain;
import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productTitle;

    private Long productPrice;

    private int count;

    private Long totalPrice;

    public Long getTotalPrice() {
        return productPrice * count;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }

    public Cart(String productTitle, Long productPrice, int count) {
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.count = count;
        this.totalPrice = count * productPrice;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void update(Cart updateCart) {
        this.count = updateCart.getCount();
    }
}




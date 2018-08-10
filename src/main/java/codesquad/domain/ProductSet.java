package codesquad.domain;


import javax.persistence.*;

@Entity
public class ProductSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Product product;

    private int count = 1;

    private Long totalPrice;

    public ProductSet(Long id, Product product, int count, Long totalPrice) {
        this.id = id;
        this.product = product;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public ProductSet() {
    }

    public ProductSet(Product product) {
        this.product = product;
        this.totalPrice = product.getPrice();
    }

    public void addCount(int count) {
        this.count += count;
        this.totalPrice += this.product.getPrice() * count;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }
}
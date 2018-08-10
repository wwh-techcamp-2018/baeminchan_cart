package codesquad.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SelectedItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    Product product;

    private Long count;

    private Long totalPrice;

    public SelectedItem() {
    }

    public SelectedItem(Product product, Long count) {
        this.product = product;
        this.count = count;
        totalPrice = calculateTotalPrice();
    }

    private Long calculateTotalPrice() {
        long totalPrice = 0;
        if(product != null && count != null)
            if(product.getPrice() != null)
             totalPrice = product.getPrice() * count;
        if(count >= 10) {
            return (long)(totalPrice * 0.95);
        }
        return  totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }


    public boolean isSameProduct(SelectedItem selectedItem) {
        return product.equals(selectedItem.getProduct());
    }


    public void addCount(Long count) {
        this.count += count;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "SelectedItem{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

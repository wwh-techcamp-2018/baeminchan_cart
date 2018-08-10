package codesquad.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Cart_id")
    private List<ProductSet> productSets = new ArrayList<ProductSet>();


    public Cart add (Product product) {
        for (int i=0; i<productSets.size(); i++){
            ProductSet pset = productSets.get(i);
            if (pset.getProduct().equals(product)) {
                pset.addCount(1);
                return this;
            }

        }
        this.productSets.add(new ProductSet(product));
        return this;
    }

    public Cart add (ProductSet productset) {
        for (int i=0; i<productSets.size(); i++){
            ProductSet pset = productSets.get(i);
            if (pset.getProduct().equals(productset.getProduct())) {
                pset.addCount(productset.getCount());
                return this;
            }

        }
        this.productSets.add(productset);
        return this;
    }

//    public Cart mergeCart(Cart cart) {
//        cart.getProductSets().forEach((pset) -> this.productSets.add(pset));
//        return this;
//    }

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    public Cart(Long id, User user, List<ProductSet> products) {
        this.id = id;
        this.user = user;
        this.productSets = products;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<ProductSet> getProductSets() {
        return productSets;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

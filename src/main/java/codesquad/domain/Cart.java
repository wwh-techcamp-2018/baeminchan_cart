package codesquad.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    private final long FREE_SHIPMENT_BOUND_PRICE = 40000;
    private final long SHIPMENT_PRICE = 4000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Cart() {
        selectedItems = new ArrayList<>();
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SelectedItem> selectedItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SelectedItem> getSelectedItems() {
        return selectedItems;
    }

    public Cart addSelectedItem(SelectedItem selectedItem) {
        for(SelectedItem item : selectedItems ) {
            if(item.isSameProduct(selectedItem)) {
                item.addCount(selectedItem.getCount());
                return this;
            }
        }
        selectedItems.add(selectedItem);
        return this;
    }

    public Long getTotalSalesPrice() {
        long totalPrice = 0;
        for(SelectedItem item : selectedItems ) {
           totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public Long getShipmentPrice() {
        return (long)(getTotalSalesPrice() >= FREE_SHIPMENT_BOUND_PRICE ? 0 : SHIPMENT_PRICE);
    }

    public Long getTotalPrice() {
        return getTotalSalesPrice() + getShipmentPrice();
    }



}

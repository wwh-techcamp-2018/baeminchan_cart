package codesquad.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    public static final Cart EMPTY_CART = new Cart();
    private final long FREE_SHIPMENT_BOUND_PRICE = 40000;
    private final long SHIPMENT_PRICE = 2500;

    public Cart() {
        selectedItems = new ArrayList<>();
    }

    private List<SelectedItem> selectedItems;

    public List<SelectedItem> getSelectedItems() {
        return selectedItems;
    }

    public int getSelectedItemCount() {
        return this.selectedItems.size();
    }

    public Cart addSelectedItem(SelectedItem selectedItem) {
        Optional<SelectedItem> maybeItem = getSelectedItemOfSameProduct(selectedItem);
        if(maybeItem.isPresent()){
            maybeItem.get().addCount(selectedItem);
            return this;
        }
        selectedItem.setId(generateSelectedItemId());
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
        return (long)(getTotalSalesPrice() >= FREE_SHIPMENT_BOUND_PRICE ? 0
                : getSelectedItemCount() == 0 ? 0 : SHIPMENT_PRICE);
    }

    public Long getTotalPrice() {
        return getTotalSalesPrice() + getShipmentPrice();
    }

    Long generateSelectedItemId() {
        if(selectedItems.size()  == 0)
            return 1L;
        return selectedItems.get(selectedItems.size() - 1).getId() + 1;
    }

    public Cart removeSelectedItem(Long id) {
        getSelectedItemById(id)
                .ifPresent(item -> {selectedItems.remove(item);});
        return this;
    }

    public Cart changeSelectedItemCount(Long id, Long buyCount) {
        getSelectedItemById(id)
                .ifPresent(item -> {item.setCount(buyCount);});
        return this;
    }

    Optional<SelectedItem> getSelectedItemById(Long id) {
        return selectedItems
                .stream()
                .filter(item -> {return item.isSameId(id);})
                .findFirst();
    }

    Optional<SelectedItem> getSelectedItemOfSameProduct(SelectedItem selectedItem) {
        return selectedItems
                .stream()
                .filter(item -> {return item.isSameProduct(selectedItem);})
                .findFirst();
    }
}

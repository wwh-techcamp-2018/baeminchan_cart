package codesquad.domain;


public class SelectedItem{


    private final Long ABLE_DISCOUNT_BOUNDARY = 10L;
    private final Double DISCOUNT_RATE = 0.95;

    private Long id;

    Product product;

    private Long count;

    public SelectedItem() {
        count = 0L;
    }

    public SelectedItem(Product product, Long count) {
        this();
        this.product = product;
        this.count = count;
    }

    private Long calculateTotalPrice() {
        long totalPrice = 0;
        if(product != null && count != null)
            if(product.getPrice() != null)
             totalPrice = product.getPrice() * count;
        if(count >= ABLE_DISCOUNT_BOUNDARY) {
            return (long)(totalPrice * DISCOUNT_RATE);
        }
        return  totalPrice;
    }

    public boolean isSameId(Long id){
        return this.id.equals(id);
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


    public void addCount(SelectedItem selectedItem) {
        this.count += selectedItem.count;
    }

    public Long getTotalPrice() {
        return calculateTotalPrice();
    }

}

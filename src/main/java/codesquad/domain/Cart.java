package codesquad.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

    private final int BORDER_PRICE = 40000;

    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<Product>();
    }

    public Cart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Cart addProducts(Product product, Long count) {

        for(int i = 0; i < count; ++i) {
            this.addProduct(product);
        }
        return this;
    }

    public Cart setProducts(Product product, Long count) {
        List<Integer> indexList = new ArrayList<Integer>();
        for(int i = 0; i < products.size(); ++i) {
            if(products.get(i).equals(product)) {
                indexList.add(i);
            }
        }

        if(indexList.size() > count) {
            int popIndex;
            while(indexList.size() != count) {
                popIndex = indexList.get(indexList.size() - 1);
                indexList.remove(indexList.size() - 1);
                products.remove(popIndex);
            }
        } else {
            addProducts(product, count - indexList.size());
        }
        return this;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }

    public Long calculatePrice() {
        Long price = calculateDisplayPrice();
        if(price > 0 && price < BORDER_PRICE) price += 2500;
        return price;

    }

    public Long calculateDisplayPrice() {
        Long price = 0L;
        Map<Product, Integer> productCount = new HashMap<Product, Integer>();
        for(int i = 0; i < products.size(); ++i) {
            Product product = products.get(i);
            int count = productCount.containsKey(product) ? productCount.get(product) : 0;
            productCount.put(product, count + 1);
        }
        for (Product product : productCount.keySet()) {
            if(productCount.get(product) > 10) price += ((int) (product.getPrice() * 0.95)) * productCount.get(product);
            else price += product.getPrice() * productCount.get(product);
        }
        return price;
    }

    public List<ProductDTO> getProductDTOList() {
        Map<Product, Integer> productCount = new HashMap<Product, Integer>();
        for(int i = 0; i < products.size(); ++i) {
            Product product = products.get(i);
            int count = productCount.containsKey(product) ? productCount.get(product) : 0;
            productCount.put(product, count + 1);
        }
        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        for (Product product : productCount.keySet()) {
            productDTOList.add(new ProductDTO(product, productCount.get(product).longValue()));
        }
        return productDTOList;

    }
}

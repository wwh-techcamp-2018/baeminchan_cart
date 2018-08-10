package codesquad.domain;

import java.util.*;

public class ShoppingBasket {

    private Map<Merchandise, Integer> merchandises = new HashMap<>();

    public ShoppingBasket() {
    }

    public ShoppingBasket(Map<Merchandise, Integer> merchandises) {
        this.merchandises = merchandises;
    }

    public void put(Merchandise merchandise) {

        Iterator<Merchandise> keys = this.merchandises.keySet().iterator();
        for (Merchandise m : merchandises.keySet()) {
            if(merchandise.equals(m)) {
                this.merchandises.put(merchandise, this.merchandises.get(m) + merchandise.getAmount());
                return;
            }
        }

        this.merchandises.put(merchandise, 1);
    }

    public Map<Merchandise, Integer> getMerchandises() {
        return merchandises;
    }
}

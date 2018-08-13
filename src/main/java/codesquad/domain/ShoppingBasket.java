package codesquad.domain;

import codesquad.dto.CartDTO;
import lombok.Getter;

import java.util.*;

@Getter
public class ShoppingBasket {

    private Map<Long, Integer> carts = new HashMap<>();

    public ShoppingBasket() {
    }

    public ShoppingBasket(Map<Long, Integer> merchandises) {
        this.carts = merchandises;
    }

    public void putCart(CartDTO cartDTO) {
        Integer count = Optional.ofNullable(carts.get(cartDTO.getId())).orElse(0);
        this.carts.put(cartDTO.getId(), count + cartDTO.getAmount());
    }

    public int getCartSize() {
        return carts.size();
    }
}

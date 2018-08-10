package codesquad.dto;

import codesquad.domain.CartItem2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO2 {

//    private List<CartItem2> itemList = new ArrayList<>();
    private Map<String, Long> itemList2 = new HashMap<>();
    private int numItem = 0;

    public CartDTO2 update(String productName, Long count) {
        if (itemList2.containsKey(productName)) {
            Long oldCount = itemList2.get(productName);
            itemList2.put(productName, oldCount + count);
        } else {
            itemList2.put(productName, count);
        }

        numItem = itemList2.size();
        return this;
    }
}

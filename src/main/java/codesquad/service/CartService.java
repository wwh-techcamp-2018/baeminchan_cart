package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.SelectedItemDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private ProductService productService;

    public Cart addSelectedItem(Cart cart, SelectedItemDTO selectedItemDTO) {
        SelectedItem newSelectedItem = new SelectedItem(productService.findById(selectedItemDTO.getProductId())
                , selectedItemDTO.getCount());
        cart.addSelectedItem(newSelectedItem);
        return cart;
    }
}

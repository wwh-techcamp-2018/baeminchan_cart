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
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SelectedItemRepository selectedItemRepository;

    public Cart saveCart(Long id , SelectedItemDTO selectedItemDTO) {
        Cart currentCart = findById(id);
        return cartRepository.save(saveCart(currentCart, selectedItemDTO));
    }

    public Cart saveCart(SelectedItemDTO selectedItemDTO) {
        Cart currentCart = new Cart();
        return cartRepository.save(saveCart(currentCart, selectedItemDTO));
    }

    private Cart saveCart(Cart cart , SelectedItemDTO selectedItemDTO) {
        SelectedItem newSelectedItem = new SelectedItem(productService.findById(selectedItemDTO.getProductId())
                , selectedItemDTO.getCount());
        selectedItemRepository.save(newSelectedItem);
        cart.addSelectedItem(newSelectedItem);
        return cart;
    }


    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }
}

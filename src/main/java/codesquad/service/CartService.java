package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartRepository;
import codesquad.dto.CartDTO2;
import codesquad.dto.CartItemPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public Cart create() {
        return cartRepository.save(new Cart());
    }

    //TODO Optional을 어떻게 생각할까
    public Cart get(Long id) {
        return cartRepository.findById(id).get();
    }

    public CartDTO2 update(CartDTO2 cartDTO2, CartItemPostDTO cartItemPostDTO) {
        return cartDTO2.update(cartItemPostDTO.getProductName(), cartItemPostDTO.getCount());
    }
}

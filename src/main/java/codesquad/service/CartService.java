package codesquad.service;

import codesquad.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Optional<Cart> findById(Long id) { return cartRepository.findById(id); }

    public Cart save() {
        return cartRepository.save(Cart.of());
    }

    // TODO : Cart 삭제 안 하면 데이터가 쌓이기만 할텐데..
}

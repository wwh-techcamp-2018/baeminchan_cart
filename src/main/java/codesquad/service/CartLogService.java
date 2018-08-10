package codesquad.service;

import codesquad.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartLogService {
    @Autowired
    private CartItemLogRepository cartItemLogRepository;

    public CartItemLog addCartLog(User user, CartItem cartItem) {
        return cartItemLogRepository.save(CartItemLog.from(user, cartItem));
    }
}

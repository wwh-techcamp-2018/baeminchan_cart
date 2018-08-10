package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.CartProduct;
import codesquad.domain.CartRepository;
import codesquad.domain.User;
import codesquad.dto.AddCartProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public Cart addToCart(AddCartProductDTO cartProductDTO, Cart cart, User user) {

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCount(cartProductDTO.getCount());

        if(cart.isEmptyCart()) {
            //hint user 비회원 확인
            cart = new Cart();
            if(!user.isGuestUser()){
                cart.setUser(user);
            }
        }
        cart.addCartProduct(cartProduct);
        return cart;
    }

    public void saveUser(User loginUser, Cart cartFromSession) {
        cartFromSession.setUser(loginUser);
        if(!cartFromSession.isEmptyCart()) {
            cartRepository.save(cartFromSession);
        }
    }
}

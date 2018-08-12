package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.CartProductDTO;
import codesquad.dto.SetCartProductDTO;
import codesquad.exception.NotAuthorizedException;
import codesquad.exception.ResourceNotFoundException;
import codesquad.support.PriceCalcultor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CartProductService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    //todo 아래 메소드를 나누고 싶은데, 단위테스트때문에 어떻게 나눠야할지 감이 안잡힌다...단위 테스트 잘못짠것같다. 더 작게 짰어야했나
    public Cart initCartProduct(CartProductDTO cartProductDTO, Cart cart, User user){
        if(cart.isEmptyCart())
            cart = new Cart();
        if(!user.isGuestUser())
            cart.setUser(user);

        cartProductDTO.fill(cart, findProductByDTO(cartProductDTO), new PriceCalcultor());
        CartProduct cartProduct = cartProductDTO.toEntity();
        return cart;
    }
    //todo @Transactional?
    @Transactional
    public Cart addToCart(CartProductDTO cartProductDTO, Cart cart, User user){
        cart = initCartProduct(cartProductDTO, cart, user);

        cartRepository.save(cart);
        return cart;
    }
    public Product findProductByDTO(CartProductDTO cartProductDTO){
        return productRepository.findById(cartProductDTO.getProductId()).orElseThrow(ResourceNotFoundException::new);
    }

    public void saveUser(User loginUser, Cart cartFromSession) {
        log.debug("Cart {}", cartFromSession);
        cartFromSession.setUser(loginUser);
        Cart cart = null;
        if(!cartFromSession.isEmptyCart()) {
            cart = cartRepository.save(cartFromSession);
        }

        log.debug("user added {}", cart);
    }

    public Cart changeCartItem(SetCartProductDTO setCartProductDTO, Cart cart, User user) {
        //todo refactor
        if(!user.isGuestUser() && !cart.getUser().equals(user)){
            throw new NotAuthorizedException();
        }
        CartProduct cartProduct = cart.getCartProducts().stream().filter(x -> x.getId() == setCartProductDTO.getCartId()).findFirst().orElseThrow(ResourceNotFoundException::new);
        cartProduct.setCount(setCartProductDTO.getCount());

        cartRepository.save(cartProduct.getCart());
        log.debug("changedCart {}", cart);
        return cart;
    }
}

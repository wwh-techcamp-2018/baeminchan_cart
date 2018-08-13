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
    @Autowired
    private CartProductRepository cartProductRepository;

    //todo 중복축되는 문제 어떻게 해결?
    public Cart initCartProduct(CartProductDTO cartProductDTO, Cart cart, User user){
        if(cart.isEmptyCart())
            cart = new Cart();
        if(!user.isGuestUser())
            cart.setUser(user);

        cartProductDTO.fill(cart, findProductByDTO(cartProductDTO), new PriceCalcultor());
        CartProduct newCartProduct = cartProductDTO.toEntity();
        Cart newCart = cartRepository.save(newCartProduct.getCart());
        return newCart;
    }
    public Cart addToCart(CartProductDTO cartProductDTO, Cart cart, User user){
        cart = initCartProduct(cartProductDTO, cart, user);


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
    @Transactional
    public Cart changeCartItem(SetCartProductDTO setCartProductDTO, Cart cart, User user) {
        //todo refactor
        log.debug("cart ", cart);
        if(!user.isGuestUser() && !cart.isOwner(user)){
            throw new NotAuthorizedException();
        }
        log.debug("setCartProductDTO {}", setCartProductDTO);
      // update가 아닌 insert 문으로 실행된다. 왜일까?
        CartProduct cartProduct = cart.getCartProducts().stream().filter(x -> x.getId() == setCartProductDTO.getCartId()).findFirst().orElseThrow(ResourceNotFoundException::new);
        cartProduct.setCount(setCartProductDTO.getCount());
        Cart changedCart = cartRepository.save(cart);
        log.debug("cartProduct Exists {}", cartProduct);


//
//        CartProduct cartProduct = cartProductRepository.findById(setCartProductDTO.getCartId()).orElseThrow(ResourceNotFoundException::new);
//        cartProduct.setCount(setCartProductDTO.getCount());
//        log.debug("cartProduct {}", cartProduct);
//        cartProduct = cartProductRepository.save(cartProduct);
        log.debug("changedCart {}", cartProduct.getCart());
        return changedCart; //cartProduct.getCart();
    }
}

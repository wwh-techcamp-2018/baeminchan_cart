package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.AddCartProductDTO;
import codesquad.dto.CartProductDTO;
import codesquad.support.PriceCalcultor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;

    @Transactional
    public Cart addToCart(AddCartProductDTO cartProductDTO, Cart cart, User user) {

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCount(cartProductDTO.getCount());

        if(cart.isEmptyCart()) {
            //hint user 비회원 확인

            if(!user.isGuestUser()){
                cart.setUser(user);
            }
        }
        cart.addCartProduct(cartProduct);
        return cart;
    }

    @Transactional
    public Cart addToCart(CartProductDTO cartProductDTO, Cart cart, User user){
        //hint productService 안에서 DTO받아서 처리?
        cartProductDTO.fill(cart, findProductByDTO(cartProductDTO), new PriceCalcultor());
        CartProduct cartProduct = cartProductDTO.toEntity();
        cart.addCartProduct(cartProduct);
        return cart;
    }
    //hint private메소드로 바꾸고 싶다.....
    //hint CartService 안에서 사용하는 ProductService 를 mock 하는 방법 없을까..?
    public Product findProductByDTO(CartProductDTO cartProductDTO){
        return productService.findById(cartProductDTO.getProductId());
    }


    private CartProductDTO fillDTO(CartProductDTO cartProductDTO, Cart cart, User user) {
        if(cart.isEmptyCart()) {
            //hint user 비회원 확인
            if(!user.isGuestUser()){
                cart.setUser(user);
            }
            cartProductDTO.setCart(cart);
        }
        return cartProductDTO;
    }

    public void saveUser(User loginUser, Cart cartFromSession) {
        cartFromSession.setUser(loginUser);
        if(!cartFromSession.isEmptyCart()) {
            cartRepository.save(cartFromSession);
        }
    }
}

//package codesquad.service;
//
//import codesquad.domain.Cart;
//import codesquad.domain.CartRepository;
//import codesquad.dto.CartDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CartService {
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    public Cart holdProduct(CartDTO cartDTO) {
//        Cart newCart = Cart.builder()
//                .numberOfProduct(cartDTO.getNumberOfProduct())
//                .product(cartDTO.getProduct())
//                .build();
//
//        return cartRepository.save(newCart);
//    }
//}

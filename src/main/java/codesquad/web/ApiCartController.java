package codesquad.web;


import codesquad.domain.Cart;
import codesquad.domain.MemorizationCart;
import codesquad.dto.SelectedItemDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/carts")
public class ApiCartController {
    
    private static final Logger log = LoggerFactory.getLogger(ApiCartController.class);

    @Autowired
    private CartService cartService;

    @PostMapping("")
    public Cart saveCart(@RequestBody SelectedItemDTO selectedItemDTO, @MemorizationCart Cart cart, HttpSession session) {
        SessionUtils.setCartInSession(session,cartService.addSelectedItem(cart, selectedItemDTO));
        log.debug("cart : {}", cart);
        return cart;
    }

    @DeleteMapping("/{id}")
    public Cart removeSelecteItem(@PathVariable Long id, @MemorizationCart Cart cart, HttpSession session) {
        SessionUtils.setCartInSession(session,cart.removeSelectedItem(id));
        return cart;
    }


    @PutMapping("/{id}/{buyCount}")
    public Cart updateSelectedItem(@PathVariable Long id
            , @MemorizationCart Cart cart
            , @PathVariable Long buyCount
            , HttpSession session) {
        SessionUtils.setCartInSession(session,cart.changeSelectedItemCount(id,buyCount));
        return cart;
    }
}

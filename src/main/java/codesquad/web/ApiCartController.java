package codesquad.web;


import codesquad.domain.Cart;
import codesquad.dto.SelectedItemDTO;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{id}")
    public Cart saveCart(@PathVariable Long id, @RequestBody SelectedItemDTO selectedItemDTO) {
        return cartService.saveCart(id, selectedItemDTO);
    }

    @PostMapping("")
    public Cart saveCart(@RequestBody SelectedItemDTO selectedItemDTO) {
        return cartService.saveCart(selectedItemDTO);
    }

}

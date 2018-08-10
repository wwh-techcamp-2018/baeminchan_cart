package codesquad.web;

import codesquad.domain.CartProduct;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BaeminchanController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model, HttpSession session) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        List<CartProduct> cartProductList = new ArrayList<>();
        List<CartProductDTO> cartList = (ArrayList)session.getAttribute(SessionUtils.CART_SESSION_KEY);
        if(cartList != null) {
            cartList.forEach((productDTO) -> {
                cartProductList.add(CartProduct.ofDTO(productDTO, productService.findById(productDTO.getId())));
            });
        }
        model.addAttribute("cartList", cartProductList);
        model.addAttribute("total", cartListTotalPrice(cartProductList));
        return "cart";
    }

    private Long cartListTotalPrice(List<CartProduct> cartList) {
        Long total = 0L;
        for(int i=0; i<cartList.size(); i++) {
            total += cartList.get(i).getPrice();
        }
//        cartList.forEach(cartProduct -> {
//            total = cartProduct.getPrice();
//        });
        return total;
    }

    @GetMapping("/order")
    public String order(Model model) {
        return "redirect:/";
    }

    @GetMapping("/")
    public String home() {
        return "/index";
    }
}

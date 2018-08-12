package codesquad.web;

import codesquad.dto.CartDTO;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class BaeminchanController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/products")
    public String products(Model model) {
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
        CartDTO cartDTO = new CartDTO(cartService.getProducts(session), productService);
        model.addAttribute("products", cartDTO.getProducts());
        return "cart";
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

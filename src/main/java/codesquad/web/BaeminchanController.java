package codesquad.web;

import codesquad.security.SessionUtils;
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

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/products/category/{categoryId}")
    public String categorizedProducts(@PathVariable Long categoryId, Model model, HttpSession session) {
        model.addAttribute("products", productService.findByCategoryId(categoryId));
        return "products";

    }

    @GetMapping("/products/{id}")
    public String product( @PathVariable Long id, Model model, HttpSession session) {
        model.addAttribute("product", productService.findById(id));
        return "product";

    }


    @GetMapping("/cart")
    public String cart(Model model) {
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

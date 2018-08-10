package codesquad.web;

import codesquad.dto.CartProductDTO;
import codesquad.dto.RequestCartProductDTO;
import codesquad.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BaeminchanController {
    @Autowired
    private ProductService productService;

    private List<CartProductDTO> productDTOs = new ArrayList<>();

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

    @ResponseBody
    @PostMapping("/cart")
    public CartProductDTO productsCart(@RequestBody RequestCartProductDTO requestCartProductDTO, HttpSession session) {
        // 해당 정보가 없으면 IllegalArgumentException 발생
        CartProductDTO cartProductDTO = productService.getProductInfo(requestCartProductDTO);
        if(!isEmptyCart(session)) {
            productDTOs = (List<CartProductDTO>) session.getAttribute("cartProductList");
        }
        productDTOs.add(cartProductDTO);
        session.setAttribute("cartProductList", productDTOs);
        return cartProductDTO;
    }

    private boolean isEmptyCart(HttpSession session) {
        return session.getAttribute("cartProductList") == null;
    }
}

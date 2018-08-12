package codesquad.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaeminchanController {
    @GetMapping("/order")
    public String order(Model model) {
        return "redirect:/";
    }

    @GetMapping("/")
    public String home() {
        return "/index";
    }
}

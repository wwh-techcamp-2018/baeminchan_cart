package codesquad.web;

import codesquad.domain.CartHistory;
import codesquad.domain.User;
import codesquad.security.SessionUtils;
import codesquad.service.CartHistoryService;
import codesquad.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CartHistoryService cartHistoryService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (SessionUtils.isLoginUser(session)) return "redirect:/";
        return "/login";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session) {
        if (SessionUtils.isLoginUser(session)) return "redirect:/";
        return "/join";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        cartHistoryService.insertCartHistory(session);
        session.invalidate();
        return "redirect:/";
    }
}

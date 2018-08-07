package codesquad.web;

import codesquad.security.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
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
        session.invalidate();
        return "redirect:/";
    }
}

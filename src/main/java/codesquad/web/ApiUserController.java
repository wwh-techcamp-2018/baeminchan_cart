package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.User;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.service.UserService;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class ApiUserController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "cartService")
    private CartService cartService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void signup(@Valid @RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(HttpSession session, @RequestBody LoginDTO loginDTO) {
        User loginUser = userService.login(loginDTO);
        SessionUtils.setUserInSession(session, loginUser);
        Cart currentCart = (Cart) session.getAttribute(SessionUtils.CART_SESSION_KEY);
        currentCart.setUser(loginUser);
        session.setAttribute(SessionUtils.CART_SESSION_KEY,currentCart);

        //TODO: change later to merging
        //session.setAttribute(SessionUtils.CART_SESSION_KEY, cartService.mergeCart(loginUser, currentCart));

    }
}

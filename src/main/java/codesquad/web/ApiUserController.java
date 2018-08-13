package codesquad.web;

import codesquad.domain.User;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartProductService;
import codesquad.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "cartProductService")
    private CartProductService cartService;
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void signup(@Valid @RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiSuccessResponse> login(HttpSession session, @RequestBody LoginDTO loginDTO) {
        User loginUser = userService.login(loginDTO);
        SessionUtils.setUserInSession(session, loginUser);

        SessionUtils.setCartInSession(session, cartService.saveUser(loginUser, SessionUtils.getCartFromSession(session)));

        return ResponseEntity.created(URI.create("/")).body(ApiSuccessResponse.builder("success"));
    }
}

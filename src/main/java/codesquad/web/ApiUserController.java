package codesquad.web;

import codesquad.domain.User;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.utils.SessionUtils;
import codesquad.service.UserService;
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
    }
}

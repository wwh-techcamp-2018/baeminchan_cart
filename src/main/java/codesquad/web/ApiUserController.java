package codesquad.web;

import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.dto.CartItemDto;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartItemService;
import codesquad.service.ProductService;
import codesquad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class ApiUserController {

    @Resource(name = "userService")
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;

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

    @PostMapping("/add-cart")
    public ResponseEntity addCart(@RequestBody CartItemDto cartItemDto, HttpSession session) {
        User user = SessionUtils.getLoginUser(session).orElseThrow(() -> new NoSuchElementException("로그인이 필요합니다."));
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.save(user, cartItemDto));
    }
}

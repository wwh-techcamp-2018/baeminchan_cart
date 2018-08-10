package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.User;
import codesquad.dto.CartDto;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import codesquad.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class ApiUserController {
    @Resource
    private CartService cartService;

    @Resource
    private ProductService productService;

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
        cartService.addAll(convertCartDtosToCarts(SessionUtils.getCartDtoFromSession(session), loginUser));
        SessionUtils.cleanSessionCart(session);
    }

    private List<Cart> convertCartDtosToCarts(List<CartDto> cartDtos, User loginUser) {
        return cartDtos
                .stream()
                .map((cartDto) -> cartDto.toCart(productService.findById(cartDto.getProductId()), loginUser))
                .collect(Collectors.toList());
    }
}

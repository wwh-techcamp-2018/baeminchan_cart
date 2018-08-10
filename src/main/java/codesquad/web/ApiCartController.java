package codesquad.web;

import codesquad.domain.Cart;
import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.dto.CartDto;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import codesquad.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/carts")
public class ApiCartController {
    @Resource
    private CartService cartService;

    @Resource
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> create(HttpSession httpSession, @RequestBody CartDto cartDto) {
        Product product = productService.findById(cartDto.getProductId());

        if (SessionUtils.isLoginUser(httpSession)) {
            cartService.create(cartDto, product, SessionUtils.getUserFromSession(httpSession));
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }

        // 로그인하지 않은 유저라면 세션에 카트 정보를 담아둔다.
        List<CartDto> cartDtos = SessionUtils.getCartDtoFromSession(httpSession);
        cartDtos.add(cartDto);
        httpSession.setAttribute(SessionUtils.USER_SESSION_CART_KEY, cartDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Cart>> list(HttpSession httpSession) {
        if(SessionUtils.isLoginUser(httpSession)) {
            return ResponseEntity.status(HttpStatus.OK).body(cartService.list(SessionUtils.getUserFromSession(httpSession)));
        }
        List<CartDto> cartDtos = SessionUtils.getCartDtoFromSession(httpSession);
        return ResponseEntity.status(HttpStatus.OK).body(convertCartDtosToCarts(cartDtos, SessionUtils.getUserFromSession(httpSession)));
    }

    private Iterable<Cart> convertCartDtosToCarts(List<CartDto> cartDtos, User loginUser) {
        return cartDtos
                .stream()
                .map((cartDto) -> cartDto.toCart(productService.findById(cartDto.getProductId()), loginUser))
                .collect(Collectors.toList());
    }
}

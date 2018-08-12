package codesquad.web;

import codesquad.domain.ResponseModel;
import codesquad.domain.User;
import codesquad.dto.CartAddDto;
import codesquad.security.SessionUtils;
import codesquad.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {
    @Autowired
    private CartService cartService;

    @PostMapping("")
    public ResponseModel<String> addProduct(@RequestBody CartAddDto dto, HttpSession session, NativeWebRequest webRequest) {
        if (!SessionUtils.isLoginUser(session)) {
            return ResponseModel.ofSuccess("");
        }
        User user = SessionUtils.getUserFromSession(webRequest);
        Long id = Long.parseLong(dto.getProductId());
        cartService.add(id, user);
        return ResponseModel.ofSuccess("Success");
    }
}

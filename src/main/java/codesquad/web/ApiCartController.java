package codesquad.web;

import codesquad.domain.CartProduct;
import codesquad.dto.CartProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ApiCartController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Void> getItem(@RequestBody CartProductDTO productDTO, HttpSession session) {
        SessionUtils.setCartInSession(session, productDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package codesquad.web;

import codesquad.dto.ProductDTO;
import codesquad.service.CartService;
import codesquad.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
@Slf4j
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<RestResponse> addCart(@Valid @RequestBody ProductDTO productDTO, HttpSession session) {

        log.info("product id :{}", productDTO.getId());
        log.info("product amount :{}", productDTO.getAmount());

        return ResponseEntity.ok(RestResponse.of(cartService.find(productDTO, session)));

    }

}

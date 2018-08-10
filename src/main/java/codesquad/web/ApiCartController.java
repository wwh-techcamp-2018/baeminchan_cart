package codesquad.web;

import codesquad.dto.CartProductDTO;
import codesquad.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    // TODO: 2018. 8. 10. 삽입, 수정, 삭제가 성공적으로 일어났는지를 상태에 따라 다르게 HttpStatus Code로 표현해야합니다.

    @GetMapping("")
    public ResponseEntity<Void> getProducts(HttpSession session) {
        log.info("카트 조회 요청합니다.");
        return new ResponseEntity(cartService.get(session), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> addProduct(HttpSession session, @RequestBody CartProductDTO cartProductDTO) {
        log.info("{} 카트에 추가 요청합니다.", cartProductDTO.toString());
        return new ResponseEntity(cartService.add(session, cartProductDTO), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(HttpSession session, @RequestBody CartProductDTO cartProductDTO) {
        log.info("{} 카트에 변경 요청합니다.", cartProductDTO.toString());
        cartService.update(session, cartProductDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteProduct(HttpSession session, @RequestBody CartProductDTO cartProductDTO) {
        log.info("{} 카트에 삭제 요청합니다.", cartProductDTO.toString());
        cartService.delete(session, cartProductDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}

package codesquad.web;

import codesquad.domain.CartHistory;
import codesquad.domain.Product;
import codesquad.domain.User;
import codesquad.dto.BasketDTO;
import codesquad.dto.BasketProductDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartHistoryService;
import codesquad.service.ProductService;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ApiProductController {

    @Autowired
    ProductService productService;

    @PutMapping("/basket")
    public ResponseEntity<Product> updateBasketEa(HttpSession session, @RequestBody BasketDTO basketDto){
        SessionUtils.setBasketInSession(session,basketDto);
        return new ResponseEntity<Product>(productService.findById(basketDto.getId()),HttpStatus.OK);
    }

    @GetMapping("/basket")
    public ResponseEntity<List<BasketProductDTO>> getBasketProductDtoList(HttpSession session){
        List<BasketProductDTO> basketProductDtoList = productService.
                getBasketProduct((List<BasketDTO>) session.getAttribute(SessionUtils.BASKET_SESSION));
        return new ResponseEntity<>(basketProductDtoList,HttpStatus.OK);
    }
}

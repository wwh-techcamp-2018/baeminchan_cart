package codesquad.web;

import codesquad.dto.BasketDto;
import codesquad.dto.BasketProductDto;
import codesquad.security.SessionUtils;
import codesquad.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/product")
public class ApiProductController {


    @Autowired
    ProductService productService;

    @PutMapping("/basket")
    public ResponseEntity<String> updateBasketEa(HttpSession session, @RequestBody BasketDto basketDto){
        productService.updateBasketEa(basketDto);
        SessionUtils.setBasketInSession(session,basketDto);
        return new ResponseEntity<String>("success",HttpStatus.OK);
    }

    @GetMapping("/basket")
    public ResponseEntity<List<BasketProductDto>> getBasketList(HttpSession session){
       List<BasketProductDto> basketProductDtoList = productService.getBasketProduct((List<BasketDto>) session.getAttribute(SessionUtils.BASKET_SESSION));
        return new ResponseEntity<>(basketProductDtoList,HttpStatus.OK);
    }

}

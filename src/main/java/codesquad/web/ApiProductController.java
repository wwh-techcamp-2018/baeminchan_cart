package codesquad.web;

import codesquad.domain.Product;
import codesquad.dto.BasketDto;
import codesquad.dto.BasketProductDto;
import codesquad.security.SessionUtils;
import codesquad.service.ProductService;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ApiProductController {

    @Autowired
    ProductService productService;

    @PutMapping("/basket")
    public ResponseEntity<Product> updateBasketEa(HttpSession session, @RequestBody BasketDto basketDto){
        SessionUtils.setBasketInSession(session,basketDto);
        return new ResponseEntity<Product>(productService.findById(basketDto.getId()),HttpStatus.OK);
    }

    @GetMapping("/basket")
    public ResponseEntity<List<BasketProductDto>> getBasketProductDtoList(HttpSession session){
        List<BasketProductDto> basketProductDtoList = productService.
                getBasketProduct((List<BasketDto>) session.getAttribute(SessionUtils.BASKET_SESSION));
        return new ResponseEntity<>(basketProductDtoList,HttpStatus.OK);
    }


}

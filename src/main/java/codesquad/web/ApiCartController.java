package codesquad.web;

import codesquad.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class ApiCartController {

    @Autowired
    private CartService cartService;

    private static final Logger log = LoggerFactory.getLogger(ApiCartController.class);

    @PostMapping("")
    public ResponseEntity<Map> addCart(@RequestBody Map<String, String> map) {
        int count = cartService.add(Long.parseLong(map.get("productId")), Integer.parseInt(map.get("quantity")));
        Map<String, String> result = new HashMap<>();
        result.put("productName", cartService.getProductById(Long.parseLong(map.get("productId"))).getDescription());
        result.put("quantity", Integer.toString(count));
        log.info("AddCart " + map.get("productId") + " and " + map.get("quantity") + " total " + count);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("")
    public ResponseEntity<Integer> getList() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartProducts().size());
    }
}

package codesquad.web;

import codesquad.domain.User;
import codesquad.dto.BasketDTO;
import codesquad.dto.OrderDTO;
import codesquad.dto.ResponseDTO;
import codesquad.security.SessionUtils;
import codesquad.service.CartHistoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/order")
public class ApiOrderController {

    @Autowired
    private CartHistoryService cartHistoryService;

    @PostMapping("")
    public ResponseDTO<Void> order(HttpSession session, @RequestBody OrderDTO orderDTO){
        //TODO 실제 주문을 집어넣을 OrderDTO와 장바구니를 비교 구매한 물건은 히스토리에 표시하여 저장

        cartHistoryService.insertCartHistory(session);
        return new ResponseDTO<>();
    }
}

package codesquad.service;

import codesquad.domain.CartHistory;
import codesquad.domain.CartHistoryRepository;
import codesquad.domain.ProductRepository;
import codesquad.domain.User;
import codesquad.dto.BasketDTO;
import codesquad.security.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CartHistoryService {

    @Autowired
    private CartHistoryRepository cartHistoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void insertCartHistory(HttpSession session) {
        User user = (User) session.getAttribute(SessionUtils.USER_SESSION_KEY);
        List<BasketDTO> basketDTOList = (List<BasketDTO>) session.getAttribute(SessionUtils.BASKET_SESSION);

        if (basketDTOList != null) {
            basketDTOList.forEach(basketDTO ->
                    cartHistoryRepository.save(new CartHistory(user,
                            productRepository.findById(basketDTO.getId()).orElse(null), basketDTO.getEa()))
            );
        }
    }


}

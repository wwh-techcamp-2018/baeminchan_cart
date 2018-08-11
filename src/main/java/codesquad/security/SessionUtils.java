package codesquad.security;

import codesquad.domain.User;
import codesquad.dto.BasketDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static final String BASKET_SESSION = "basketId";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }


    public static void setBasketInSession(HttpSession session, BasketDTO basketDto) {
        List<BasketDTO> maybebasketDtoList = (List<BasketDTO>) session.getAttribute(BASKET_SESSION);

        if (maybebasketDtoList == null) {
            maybebasketDtoList = new ArrayList<>();
            maybebasketDtoList.add(basketDto);
            session.setAttribute(BASKET_SESSION, maybebasketDtoList);
            return;
        }

        session.setAttribute(BASKET_SESSION, basketDto.updateBasket(maybebasketDtoList));
    }

}

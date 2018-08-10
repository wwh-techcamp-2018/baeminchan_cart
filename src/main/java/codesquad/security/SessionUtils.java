package codesquad.security;

import codesquad.domain.User;
import codesquad.dto.BasketDto;
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

    public static void setBasketInSession(HttpSession session, BasketDto basketDto) {
        List<BasketDto> maybebasketDtoList = (List<BasketDto>) session.getAttribute(BASKET_SESSION);
        if (maybebasketDtoList == null) {
            maybebasketDtoList = new ArrayList<>();
            maybebasketDtoList.add(basketDto);
            session.setAttribute(BASKET_SESSION, maybebasketDtoList);
            return;
        }
        basketDto.updateBasket(maybebasketDtoList);
        session.setAttribute(BASKET_SESSION, maybebasketDtoList);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }

}

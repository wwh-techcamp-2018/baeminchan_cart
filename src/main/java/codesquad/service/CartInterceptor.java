package codesquad.service;

import codesquad.domain.Cart;
import codesquad.domain.Order;
import codesquad.security.SessionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class CartInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(!hasCart(session)) initCart(session);
        return true;
    }

    private boolean hasCart(HttpSession session){
        return session.getAttribute(SessionUtils.CART_SESSSION_KEY) != null;
    }

    private void initCart(HttpSession session){
        session.setAttribute(SessionUtils.CART_SESSSION_KEY, new Order());
    }
}

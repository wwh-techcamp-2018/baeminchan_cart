package codesquad.aspect;

import codesquad.domain.Cart;
import codesquad.domain.CartLog;
import codesquad.domain.CartLogRepository;
import codesquad.security.SessionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Aspect
@Component
public class CartLogAspect {

    @Autowired
    private CartLogRepository cartLogRepository;

    @Pointcut("execution(public * codesquad.web.ApiCartController.*Product(Long, Integer, codesquad.domain.Cart))")
    public void cartApiEndpoints() {

    }

    @After("cartApiEndpoints() && args(productId, count, sessionCart)")
    public void doLogging(JoinPoint joinPoint, Long productId, Integer count, Cart sessionCart) {
        final Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        final String methodName = joinPoint.getSignature().getName();
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final HttpSession session = request.getSession();
        final String clientIp = request.getRemoteAddr();

        log.info("productId: {}, count: {}", productId, count);
        CartLog cartLog = new CartLog(methodName, productId, count, clientIp, LocalDateTime.now());
        SessionUtils.setCartInSession(session, sessionCart);
        log.info("{}", cartLogRepository.save(cartLog));
    }
}

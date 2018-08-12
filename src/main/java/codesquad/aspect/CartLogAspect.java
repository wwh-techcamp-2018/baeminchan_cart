package codesquad.aspect;

import codesquad.domain.CartLog;
import codesquad.domain.CartLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class CartLogAspect {

    @Autowired
    private CartLogRepository cartLogRepository;

    @After("execution(* codesquad.service.CartService.*(..))")
    public void doLogging(JoinPoint joinPoint) throws Throwable {
        final Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        final String methodName = joinPoint.getSignature().getName();

        if (methodName.startsWith("get")) {
            return;
        }

        CartLog cartLog = new CartLog(methodName, (Long) joinPoint.getArgs()[1], (Integer) joinPoint.getArgs()[2], LocalDateTime.now());
        log.info("{}", cartLogRepository.save(cartLog));
    }

}

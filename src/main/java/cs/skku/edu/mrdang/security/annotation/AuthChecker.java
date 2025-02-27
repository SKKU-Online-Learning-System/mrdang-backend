package cs.skku.edu.mrdang.security.annotation;

import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.exception.ErrorCode;
import cs.skku.edu.mrdang.exception.RestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuthChecker {
    @Before("@annotation(cs.skku.edu.mrdang.security.annotation.Auth)")
    public void check(final JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .filter(user -> user != null)
                .findFirst()
                .orElseThrow(() -> new RestException(ErrorCode.INVALID_ADMIN_AUTHORITY));
    }
}
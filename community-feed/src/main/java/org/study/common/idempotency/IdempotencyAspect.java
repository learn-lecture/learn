package org.study.common.idempotency;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.study.common.ui.Response;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotencyAspect {

    private final IdempotencyRepository idempotencyRepository;
    private final HttpServletRequest request;

    @Around("@annotation(Idempotent)")
    public Object checkIdempotency(ProceedingJoinPoint joinPoint) throws Throwable {
        String idempotencyKey = request.getHeader("idempotency-Key");
        if (idempotencyKey == null) {
            return joinPoint.proceed();
        }

        Idempotency idempotency = idempotencyRepository.getByKey(idempotencyKey);

        if (idempotency != null) {
            return idempotency.getResponse();
        }

        Object result = joinPoint.proceed();

        Idempotency newIdempotency = new Idempotency(idempotencyKey, (Response<?>) result);
        idempotencyRepository.save(newIdempotency);

        return result;
    }
}

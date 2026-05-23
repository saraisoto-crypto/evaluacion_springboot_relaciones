package com.tecsup.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorAspect {

    private static final Logger log = LoggerFactory.getLogger(ErrorAspect.class);

    @AfterThrowing(pointcut = "execution(* com.tecsup.service.*.*(..))", throwing = "ex")
    public void capturarError(JoinPoint jp, Exception ex) {
        log.error("Error en {}: {}", jp.getSignature().getName(), ex.getMessage());
    }
}
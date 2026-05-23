package com.tecsup.aspect;

import com.tecsup.model.AuditoriaLog;
import com.tecsup.repository.AuditoriaLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaLogRepository repo;

    @After("execution(* com.tecsup.service.*.*(..))")
    public void auditar(JoinPoint jp) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuario = (auth != null) ? auth.getName() : "anonimo";

        AuditoriaLog log = new AuditoriaLog();
        log.setAccion(jp.getSignature().getName());
        log.setMetodo(jp.getSignature().toShortString());
        log.setFecha(new Timestamp(System.currentTimeMillis()));
        log.setUsuario(usuario);
        repo.save(log);
    }
}
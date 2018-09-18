package com.ty.show.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.ty.show.controller.*.*.*(..))")
    public void log() {
        log.info("");
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求地址【{}】", request.getRequestURL());
        log.info("请求类型【{}】", request.getMethod());
        log.info("请求IP【{}】", request.getRemoteAddr());
        log.info("请求方法【{}】", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数【{}】", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        log.info("");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("请求返回值【{}】", object.toString());
    }
}

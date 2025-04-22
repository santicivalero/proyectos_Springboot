package com.santi.curso.springboot.app.aop.springboot_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GreetingServicePointcuts {

    @Pointcut("execution(String com.santi.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void greetingLoggerPointcut() {
    }

    @Pointcut("execution(String com.santi.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void greetingFooLoggerPointcut() {
    }

}

package com.example.stripepayment.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(com.example.stripepayment.service.FullNameComposer)")
    public void stringProcessingMethods() {
    };

    @After("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature()
                .getName();

        logger.log(Level.INFO, "название метода: " + methodName);
    }

    @AfterReturning(pointcut = "execution(public String com.example.stripepayment.service.FullNameComposer.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        logger.log(Level.INFO, "возвращенное значение: " + result.toString());
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.log(Level.INFO, joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }

}

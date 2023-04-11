package com.miki.testExample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Before - сработает перед выполнением метода
     * After — после вызова метода
     * After returning — после возврата значения из функции
     * After throwing — в случае exception
     * After finally — в случае выполнения блока finally
     * Around — можно сделать пред., пост., обработку перед вызовом метода, а также вообще обойти вызов метода
     * Аннотация в начале класса Order- дает преимущество выполнения одинаковым аспектам @Order(1,2,3...)
     * в скобках указывается pointcut - выполнение сквозного кода
     * execution - перед выполнением какого-то метода
     * ..- любые параметры
     * JoinPoint - получаем информацию о сигнатуре метода
     */
    @Before("execution(* com.miki.testExample.manager.UserManager.save(..))")
    public void beforeAddNewUserAdvice(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        logger.info("New user");
        logger.info(signature.getDeclaringType().toString());
        logger.info(Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * * - любое имя в пути и любые параметры
     * Шаблон, который можно использовать в дальнейшем
     */
    @Pointcut("execution(* com.miki.testExample.manager.*.*(..))")
    public void userModified() {
    }

    @Before("LoggingAspect.userModified()")
    public void beforePointCutAddNewUserAdvice() {
        logger.info("Some modified users");
    }

    /**
     * Люболе продолжение после *
     */
    @Before("execution(* com.miki.testExample.manager.UserManager.get*(..))")
    public void beforeGetAllUsersAdvice() {
        logger.info("Getting allUsers");
    }

    /**
     * ProceedingJoinPoint - будем вручную запускать таргет метод
     * мы вручную сами запускаем метод и после этого возвращаем результат
     * результат Object, так же часто возвращаемое значение указывается Object
     */
    @Around("LoggingAspect.userModified()")
    public Object aroundPointCutAddNewUserAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Some modified getting users");
        Object proceed = proceedingJoinPoint.proceed();
        logger.info("Some modified  users exit");
        return proceed;
    }


}

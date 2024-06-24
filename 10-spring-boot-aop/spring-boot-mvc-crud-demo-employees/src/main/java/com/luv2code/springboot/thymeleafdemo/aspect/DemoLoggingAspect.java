package com.luv2code.springboot.thymeleafdemo.aspect;

import jakarta.persistence.JoinTable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // set up logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {}


    //combine pointcut
    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {}

    //add @before advice
    public void before(JoinPoint thejoinPoint) {

        //display method we are calling
        String theMethod = thejoinPoint.getSignature().toShortString();
        myLogger.info("======> in @Before : calling method : " + theMethod);

        //display the argument to the method

        //get the argument
        Object[] args = thejoinPoint.getArgs();

        // loop thru and display args
        for (Object tempArg : args) {
            myLogger.info("======> argument : " + tempArg);
        }
    }
        // add @AfterReturning advice
        @AfterReturning(
                pointcut = "forAppFlow",
                returning = "theResult")
                public void afterReturning(JoinPoint thejoinPoint, Object theResult){

                    // display method we are returning from
                    String theMethod = thejoinPoint.getSignature().toShortString();
                    myLogger.info("======> @AfterReturning : from method: " + theMethod);

                    // display data returned
                    myLogger.info("=======> result: "+theResult);
                }
}








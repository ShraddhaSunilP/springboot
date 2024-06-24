package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
        //print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> executing @Around on method : "+method);
            // get begin timestamp
            long begin = System.currentTimeMillis();
            // now, let's execute the method
            Object result = null;
            try{
               result = theProceedingJoinPoint.proceed();
            }
            catch(Exception exc){
                // log the exception
                System.out.println(exc.getMessage());  // log the exception

                //rethrow exception
                throw exc;
            }
            long end = System.currentTimeMillis();  // get end timestamp
            // compute duration and display
            long duration = end - begin;
            System.out.println("\n ======> Duration: " + duration /1000.0 + "seconds");
            return result;
        }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> executing @After (finally) on method : "+method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc){

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> executing @AfterThrowing on method : "+method);

        //log the exception
        System.out.println("\n =====>>> The exception is : "+theExc);

    }

    @AfterReturning(
           pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> executing @AfterReturning on method : "+method);

        // print out the result of the method call
        System.out.println("\n ====> result is : "+ result);

        // post-process the data ... modify it
        // convert the account names to uppercase
        convertAccountNameToUpperCase(result);

        System.out.println("\n ====> result is : "+ result);
    }

    private void convertAccountNameToUpperCase(List<Account> result) {

        // loop through accounts
        for(Account tempAccount : result){

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update thename on the account
            tempAccount.setName(theUpperName);
        }
    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint){
        System.out.println("\n=======>>> @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();
        System.out.println("Method: "+methodSignature);

        // display method arguments
        // get args
        Object[] args = theJointPoint.getArgs();

        // loop thru args
        for(Object tempArg : args){
            System.out.println(tempArg);
            if(tempArg instanceof Account){
                //downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: "+ theAccount.getName());
                System.out.println("account level: "+ theAccount.getLevel());
            }
        }
    }
}








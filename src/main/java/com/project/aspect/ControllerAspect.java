package com.project.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by jedaka on 10.11.2015.
 */
@Aspect
public class ControllerAspect {

    private Logger logger;

    /**
     * Logging logic before executing method's body.
     * Log method arguments
     *
     * @param joinPoint method's information
     */
    @Before(value = "controllerPointCut()")
    public void controllerAdviceBefore(JoinPoint joinPoint) {

        logger = Logger.getLogger(joinPoint.getTarget().getClass());

        Object[] args = joinPoint.getArgs();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(joinPoint.getSignature().toShortString()).append(" | ");


        if (args.length > 0) {
            stringBuilder.append("Method arguments: ");
            for (Object arg : args) {
                stringBuilder.append(" ").append(arg);
            }

            logger.info(stringBuilder);
        }

    }

    /**
     * Logging logic after executing method's body
     * Log method return value
     *
     * @param joinPoint   method's information
     * @param returnValue return value
     */
    @AfterReturning(value = "controllerPointCut()", returning = "returnValue")
    public void controllerAdviceAfter(JoinPoint joinPoint, Object returnValue) {
        logger = Logger.getLogger(joinPoint.getTarget().getClass());
        if (returnValue != null) {
            logger.info(joinPoint.getSignature().toShortString() + " | Return: " + returnValue.toString());
        }
    }

    /**
     * Logging logic in case of exception
     * Log exception
     *
     * @param joinPoint method's information
     * @param e         exception
     */
    @AfterThrowing(value = "controllerPointCut()", throwing = "e")
    public void controllerAdviceAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger = Logger.getLogger(joinPoint.getTarget().getClass());
        logger.warn(joinPoint.getSignature().toShortString() + " | Throws: " + e.getMessage());
        e.printStackTrace();
    }

    /**
     * Pointcut where advice take place
     */
    @Pointcut("execution(* com.project.mvc.*.*(..))")
    public void controllerPointCut() {
    }

}

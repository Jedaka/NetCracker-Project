package com.project.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by jedaka on 10.11.2015.
 */
@Aspect
public class ControllerAspect {

    private Logger logger;

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

    @AfterReturning(value = "controllerPointCut()", returning = "returnValue")
    public void controllerAdviceAfter(JoinPoint joinPoint, Object returnValue) {
        logger = Logger.getLogger(joinPoint.getTarget().getClass());
        logger.info(joinPoint.getSignature().toShortString() + " | Return: " + returnValue.toString());

    }

    @Pointcut("execution(* com.project.mvc.*.*(..))")
    public void controllerPointCut() {
    }

}

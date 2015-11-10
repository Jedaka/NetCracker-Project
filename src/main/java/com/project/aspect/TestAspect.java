package com.project.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by jedaka on 10.11.2015.
 */
@Aspect
public class TestAspect {

    private Logger logger;

    @Before("execution(* com.project.*.*.*(..))")
    public void testAdvice(JoinPoint joinPoint){
        logger = Logger.getLogger(joinPoint.getTarget().getClass());
        logger.info(joinPoint.getSignature().toShortString());
    }

}

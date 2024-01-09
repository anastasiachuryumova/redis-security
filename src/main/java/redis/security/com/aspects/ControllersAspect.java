package redis.security.com.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllersAspect {

    private static Logger logger = LogManager.getLogger();

    @Pointcut("within(redis.security.com.controllers..*)")
    public void callAtMyServicePublic () { }

    @Around("callAtMyServicePublic()")
    public Object afterCallAt (ProceedingJoinPoint jp) {
        try {
            logger.info(jp.toString());
            return jp.proceed();
        } catch (Throwable e) {
            String methodName = jp.getSignature().getName();
            logger.error("Exception " + methodName + ":\n" + e.getMessage());
            return "error";
        } finally {
            logger.info("Controller end work " + jp.toString());
        }
    }
}

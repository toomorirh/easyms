package com.rhcsa.easyms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
@Aspect
@Component
public class LoggingAspect {
 
    Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Before("within(com.rhcsa.easyms.controller.*)")
    public void preLog(JoinPoint jp) {
        logger.info("Execution Method @Before preLog");
        logger.info("Method Name" + jp.getSignature().toString());
    }
 
    @After("within(com.rhcsa.easyms.controller.*)")
    public void postLog(JoinPoint jp) {
        logger.info("Execution Method @After postLog");
        logger.info("Method Name" + jp.getSignature().toString());
    }
}

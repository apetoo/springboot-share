package com.warape.springbootshare.integrated.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : wanmingyu
 * @date : 2019/5/5 10:38 AM
 * 时间和结果记录切面，收集接口的运行时间
 */
@Aspect
@Component
public class ResponseAspect {

    //设置切点
//    @Pointcut("execution(* com.warape.springbootshare.integrated.controller..*.*(..))")
    @Pointcut("@annotation(com.warape.springbootshare.integrated.annotation.TimerAop)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标Logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        // 获取目标类名称
//        String clazzName = joinPoint.getTarget().getClass().getName();

        // 获取目标类方法名称
//        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        // 调用目标方法
        Object result = joinPoint.proceed();
        logger.info("耗时:{}",System.currentTimeMillis() - start);
        return result;
    }
}
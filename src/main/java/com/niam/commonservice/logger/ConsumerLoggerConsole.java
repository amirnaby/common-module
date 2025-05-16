package com.niam.commonservice.logger;

import com.niam.commonservice.model.logger.JoinInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@RequiredArgsConstructor
@Component
@Slf4j
@Profile("logger-off")
public class ConsumerLoggerConsole {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.niam.commonservice.annotation.Auditable)")
    public Object logRestControllerExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        JoinInfo joinInfo = JoinInfo.builder()
                .methodName(joinPoint.getSignature().getName())
                .className(joinPoint.getTarget().getClass().getName())
                .methodArgs(joinPoint.getArgs()).build();
        if (!joinInfo.getMethodName().equals("listen") && !joinInfo.getMethodName().equals("dlqListener")) {
            logger.info("Executing {}.{}() with arguments: {}", joinInfo.getClassName(), joinInfo.getMethodName(),
                    Arrays.toString(joinInfo.getMethodArgs()));
        }

        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        if (!joinInfo.getMethodName().equals("listen") && !joinInfo.getMethodName().equals("dlqListener")) {
            logger.info("{}.{}() executed in {} ms with result: {}",
                    joinInfo.getClassName(), joinInfo.getMethodName(), endTime - startTime, result);
        }
        return result;
    }
}
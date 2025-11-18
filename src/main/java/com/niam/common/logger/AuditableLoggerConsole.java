package com.niam.common.logger;

import com.niam.common.model.logger.JoinInfo;
import com.niam.common.utils.ObjectConverter;
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
@Profile("!kafka-logger")
public class AuditableLoggerConsole {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectConverter objectConverter;

    @Around("@annotation(com.niam.common.annotation.Auditable)")
    public Object logRestControllerExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object[] sanitizedArgs = Arrays.stream(joinPoint.getArgs())
                .map(objectConverter::sanitize)
                .toArray();

        JoinInfo joinInfo = JoinInfo.builder()
                .methodName(joinPoint.getSignature().getName())
                .className(joinPoint.getTarget().getClass().getName())
                .methodArgs(sanitizedArgs)
                .build();

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
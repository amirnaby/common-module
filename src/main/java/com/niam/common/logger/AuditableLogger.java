package com.niam.common.logger;

import com.niam.common.model.logger.JoinInfo;
import com.niam.common.model.logger.LogModelDto;
import com.niam.common.model.response.ResultLevel;
import com.niam.common.model.response.ServiceResponse;
import com.niam.common.producer.KafkaProducerService;
import com.niam.common.utils.ObjectConverter;
import com.niam.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Aspect
@RequiredArgsConstructor
@Component
@Slf4j
@Profile("kafka-logger")
public class AuditableLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaProducerService kafkaProducerService;
    private final BuildProperties buildProperties;
    private final ObjectConverter objectConverter;
    @Value("${common.log.level}")
    private String logLevel;
    @Value("${common.logger.log-topic}")
    private String topicName;

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
            logger.info("{}.{}() executed in {} ms with result: {}", joinInfo.getClassName(), joinInfo.getMethodName(),
                    endTime - startTime, result);
        }
        if (logLevel.equals(ResultLevel.INFO.name()) && !joinInfo.getMethodName().equals("listen")) {
            fillLogModel(startTime, endTime, joinPoint, joinInfo, result);
        }
        return result;
    }

    @Async("taskExecutor")
    protected void fillLogModel(Long startTime, Long endTime, ProceedingJoinPoint joinPoint, JoinInfo joinInfo,
                                Object result) {
        String serverIp = Utils.getServerIP();
        Utils.TrackingNumbers trackingNumbers = Utils.getTrackingNumbers(joinPoint);
        String requestBody;
        try {
            requestBody = objectConverter.convertToJsonString(Arrays.asList(joinPoint.getArgs()).getFirst());
        } catch (Exception e) {
            requestBody = Arrays.toString(joinPoint.getArgs());
        }
        LogModelDto.LogModelDtoBuilder builder = LogModelDto.builder()
                .logLevel(ResultLevel.INFO.name())
                .methodName(joinInfo.getMethodName())
                .ipServer(serverIp)
                .appVersion(buildProperties.getVersion())
                .serviceName(buildProperties.getArtifact())
                .requestDateTime(LocalDateTime.ofEpochSecond(startTime / 1000, 0,
                        ZoneOffset.ofHoursMinutes(3, 30)))
                .responseDateTime(LocalDateTime.ofEpochSecond(endTime / 1000, 0,
                        ZoneOffset.ofHoursMinutes(3, 30)))
                .request(requestBody)
                .elapsedTime(endTime - startTime)
                .response(result == null ? "" : objectConverter.convertToJsonString(result))
                .trackCode(trackingNumbers.trackCodeValue())
                .externalTrackCode(trackingNumbers.externalTrackCodeValue());

        if (result instanceof ResponseEntity<?> responseEntity &&
                (responseEntity.getBody() instanceof ServiceResponse serviceResponse)) {
            builder.responseCode(serviceResponse.getResultResponse().getResponseCode())
                    .reasonCode(serviceResponse.getResultResponse().getReasonCode())
                    .responseDescription(serviceResponse.getResultResponse().getResponseDescription())
                    .response(serviceResponse.getData() == null ? null : serviceResponse.getData().toString());
        }
        LogModelDto logModel = builder.build();
        kafkaProducerService.produce(topicName, logModel);
    }
}
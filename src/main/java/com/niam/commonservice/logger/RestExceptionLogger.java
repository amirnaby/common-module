package com.niam.commonservice.logger;

import com.niam.commonservice.exception.BusinessException;
import com.niam.commonservice.model.logger.LogModelDto;
import com.niam.commonservice.model.response.ResultLevel;
import com.niam.commonservice.producer.KafkaProducerService;
import com.niam.commonservice.utils.ObjectConverter;
import com.niam.commonservice.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
@Profile("!logger-off")
public class RestExceptionLogger {
    private final KafkaProducerService kafkaProducerService;
    private final BuildProperties buildProperties;
    private final ObjectConverter objectConverter;
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    @Value("${common.log.level}")
    private String logLevel;
    @Value("${common.logger.log-topic}")
    private String topicName;

    @AfterThrowing(pointcut = "execution(* com.behsa.*.controller.*.*(..))", throwing = "ex")
    public void logExecution(JoinPoint joinPoint, Exception ex) {
        if (logLevel.equals(ResultLevel.INFO.name()) || logLevel.equals(ResultLevel.WARN.name()) ||
                logLevel.equals(ResultLevel.ERROR.name())) {
            fillLogModel(joinPoint, ex, buildProperties.getArtifact(), buildProperties.getVersion());
        }
    }

    @Async("taskExecutor")
    protected void fillLogModel(JoinPoint joinPoint, Exception ex, String artifact, String version) {
        long endTime = System.currentTimeMillis();
        long startedTime = startTime.get();
        long executionTime = endTime - startedTime;
        startTime.remove();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getResponse();
        String methodType = request.getMethod();
        String methodName = joinPoint.getSignature().getName();

        String requestString;
        try {
            requestString = objectConverter.convertToJsonString(Arrays.asList(joinPoint.getArgs()).get(0));
        } catch (Exception e) {
            requestString = Arrays.toString(joinPoint.getArgs());
        }
        Utils.TrackingNumbers trackingNumbers = Utils.getTrackingNumbers(joinPoint);
        Integer httpStatus = response != null ? response.getStatus() : -1;

        LogModelDto.LogModelDtoBuilder builder = LogModelDto.builder()
                .errorDescription(ex.getMessage())
                .logLevel(ResultLevel.ERROR.name())
                .methodName(methodName)
                .httpMethod(methodType)
                .httpStatus(httpStatus)
                .appVersion(version)
                .serviceName(artifact)
                .exceptionType(ex.getClass().getCanonicalName())
                .ipServer(Utils.getServerIP())
                .request(requestString)
                .elapsedTime(executionTime)
                .responseDateTime(LocalDateTime.ofEpochSecond(endTime / 1000, 0,
                        ZoneOffset.ofHoursMinutes(3, 30)))
                .requestDateTime(LocalDateTime.ofEpochSecond(startedTime / 1000, 0,
                        ZoneOffset.ofHoursMinutes(3, 30)))
                .trackCode(trackingNumbers.trackCodeValue())
                .externalTrackCode(trackingNumbers.externalTrackCodeValue());

        if (ex instanceof BusinessException businessException) {
            builder.responseDescription(businessException.getResponseDescription())
                    .reasonCode(businessException.getReasonCode())
                    .responseCode(businessException.getResponseCode());
        }
        LogModelDto logModel = builder.build();
        kafkaProducerService.produce(topicName, logModel);
    }

    @Before("execution(* com.behsa.*.controller.*.*(..))")
    public void beforeExecution() {
        startTime.set(System.currentTimeMillis());
    }
}
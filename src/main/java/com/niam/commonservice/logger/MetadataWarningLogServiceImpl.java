package com.niam.commonservice.logger;

import com.niam.commonservice.model.logger.LogModelDto;
import com.niam.commonservice.model.response.ResultLevel;
import com.niam.commonservice.producer.KafkaProducerService;
import com.niam.commonservice.utils.ObjectConverter;
import com.niam.commonservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("!logger-off")
public class MetadataWarningLogServiceImpl implements IMetadataWarningLog {
    private final KafkaProducerService kafkaProducerService;
    private final BuildProperties buildProperties;
    private final ObjectConverter objectConverter;
    String serverIp = Utils.getServerIP();
    @Value("${common.log.level}")
    private String commonLogLevel;
    @Value("${common.logger.log-topic}")
    private String topicName;

    public void warningLogPublisher(String methodName, String trackCode, String externalTrackCode, String request,
                                    String description) {
        if (commonLogLevel.equals(ResultLevel.WARN.name()) || commonLogLevel.equals(ResultLevel.INFO.name())) {
            String requestBody;
            try {
                requestBody = objectConverter.convertToJsonString(request);
            } catch (Exception e) {
                requestBody = request;
            }
            LogModelDto logModel = LogModelDto.builder()
                    .logLevel(LogLevel.WARN.name())
                    .methodName(methodName)
                    .ipServer(serverIp)
                    .appVersion(buildProperties.getVersion())
                    .serviceName(buildProperties.getArtifact())
                    .requestDateTime(LocalDateTime.now())
                    .request(requestBody)
                    .description(description)
                    .trackCode(trackCode)
                    .externalTrackCode(externalTrackCode).build();

            kafkaProducerService.produce(topicName, logModel);
        }
    }
}
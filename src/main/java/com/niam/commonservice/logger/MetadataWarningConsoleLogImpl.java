package com.niam.commonservice.logger;

import com.niam.commonservice.model.logger.LogModelDto;
import com.niam.commonservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("logger-off")
public class MetadataWarningConsoleLogImpl implements IMetadataWarningLog {
    private final BuildProperties buildProperties;
    String serverIp = Utils.getServerIP();

    public void warningLogPublisher
            (String methodName, String trackCode, String externalTrackCode, String request, String description) {
        LogModelDto logModel = LogModelDto.builder()
                .logLevel(LogLevel.WARN.name())
                .methodName(methodName)
                .ipServer(serverIp)
                .appVersion(buildProperties.getVersion())
                .serviceName(buildProperties.getArtifact())
                .requestDateTime(LocalDateTime.now())
                .request(request)
                .description(description)
                .trackCode(trackCode)
                .externalTrackCode(externalTrackCode).build();

        log.warn(logModel.toString());
    }
}
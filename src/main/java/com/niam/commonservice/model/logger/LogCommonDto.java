package com.niam.commonservice.model.logger;

import com.niam.commonservice.utils.CustomLocalDateTimeDeserializer;
import com.niam.commonservice.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class LogCommonDto implements Serializable {
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime requestDateTime;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime responseDateTime;
    private Long elapsedTime;
    private String logLevel;
    private String request;
    private Integer httpStatus;
    private String response;
    private String ipServer;
    private String httpMethod;
    private String trackCode;
    private String appVersion;
    private String externalTrackCode;
    private String exceptionType;
}
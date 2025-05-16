package com.niam.commonservice.model.logger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.niam.commonservice.utils.CustomLocalDateTimeDeserializer;
import com.niam.commonservice.utils.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InquiryLoggerDto implements Serializable {
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime requestDateTime;
    private String documentId;
    private String documentCode;
    private Long documentType;
    private String documentOwner;
    private String ownerMobileNo;
    private Integer originalSystem;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime requested_At_From;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime requested_At_To;
    private Integer inquiryObjectLevel;
    private String externalTrackCode;
    private Boolean allVersions;
    private String trackCodes;
}
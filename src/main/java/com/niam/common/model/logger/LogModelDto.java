package com.niam.common.model.logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class LogModelDto extends LogCommonDto {
    private Integer responseCode;
    private Integer reasonCode;
    private String responseDescription;
    private String serviceName;
    private String errorDescription;
    private String description;
    private String methodName;
}
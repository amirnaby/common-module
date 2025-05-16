package com.niam.commonservice.model.logger;

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
public class OrchestratorLogModelDto extends LogCommonDto {
    private String requestTo;
    private String routeName;
    private String exceptionRote;
    private Integer responseCode;
    private Integer reasonCode;
    private String responseDescription;
}
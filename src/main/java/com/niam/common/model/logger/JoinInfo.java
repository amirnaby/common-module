package com.niam.common.model.logger;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JoinInfo {
    private String methodName;
    private String methodType;
    private String className;
    private Object[] methodArgs;
}
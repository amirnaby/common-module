package com.niam.common.utils;

import com.niam.common.exception.ResultResponseStatus;
import com.niam.common.model.response.ResultLevel;
import com.niam.common.model.response.ResultResponse;
import com.niam.common.model.response.ServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResponseEntityUtil {
    private final MessageUtil messageUtil;

    public ResponseEntity<ServiceResponse> ok(Object data) {
        ServiceResponse response = new ServiceResponse();
        response.setData(data);
        ResultResponse resultResponse = new ResultResponse();
        return getServiceResponseResponseEntity(response, resultResponse);
    }

    private ResponseEntity<ServiceResponse> getServiceResponseResponseEntity(ServiceResponse response, ResultResponse resultResponse) {
        resultResponse.setResponseCode(ResultResponseStatus.SUCCESS.getResponseCode());
        resultResponse.setReasonCode(ResultResponseStatus.SUCCESS.getReasonCode());
        resultResponse.setResponseDescription(messageUtil.getMessage(ResultResponseStatus.SUCCESS.getDescription()));
        resultResponse.setLevel(ResultLevel.INFO);
        response.setResultResponse(resultResponse);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ServiceResponse> ok() {
        ServiceResponse response = new ServiceResponse();
        ResultResponse resultResponse = new ResultResponse();
        return getServiceResponseResponseEntity(response, resultResponse);
    }

    private ResponseEntity<ServiceResponse> badRequest(Object data) {
        ServiceResponse response = new ServiceResponse();
        response.setData(data);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResponseCode(ResultResponseStatus.FAILURE.getResponseCode());
        resultResponse.setReasonCode(ResultResponseStatus.FAILURE.getReasonCode());
        resultResponse.setResponseDescription(messageUtil.getMessage(ResultResponseStatus.FAILURE.getDescription()));
        resultResponse.setLevel(ResultLevel.WARN);
        response.setResultResponse(resultResponse);
        return org.springframework.http.ResponseEntity.ok(response);
    }
}
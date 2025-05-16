package com.niam.commonservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niam.commonservice.exception.ResultResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ServiceResponse {
    private ResultResponse resultResponse;
    @Setter
    @Getter
    private Object data;

    @JsonProperty
    public void setResultResponse(ResultResponse resultResponse) {
        this.resultResponse = resultResponse;
    }

    public void setResultResponse(ResultResponseStatus resultStatus) {
        if (resultStatus == null) {
            return;
        }
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResponseCode(resultStatus.getResponseCode());
        resultResponse.setReasonCode(resultStatus.getReasonCode());
        resultResponse.setResponseDescription(resultStatus.getDescription());
        resultResponse.setLevel(resultResponse.getResultLevel(resultStatus));
        this.resultResponse = resultResponse;
    }

    public void setResultResponse(ResultResponseStatus resultStatus, String message) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResponseCode(resultStatus.getResponseCode());
        resultResponse.setReasonCode(resultStatus.getReasonCode());
        resultResponse.setResponseDescription(resultStatus.getDescription());
        resultResponse.setLevel(resultResponse.getResultLevel(resultStatus));
        this.resultResponse = resultResponse;
    }

    public void setResultResponse(ResultResponseStatus resultStatus, boolean isExternal) {
        if (resultStatus == null) {
            return;
        }
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResponseCode(resultStatus.getResponseCode());
        resultResponse.setReasonCode(resultStatus.getReasonCode());
        resultResponse.setResponseDescription(resultStatus.getDescription());

        if (!isExternal) {
            resultResponse.setResponseCode(resultStatus.getResponseCode());
        }
        resultResponse.setLevel(resultResponse.getResultLevel(resultStatus));
        this.resultResponse = resultResponse;
    }
}
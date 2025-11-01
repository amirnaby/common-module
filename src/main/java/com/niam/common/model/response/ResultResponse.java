package com.niam.common.model.response;

import com.niam.common.exception.ResultResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ResultResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 6091567334208093240L;
    private Integer responseCode;
    private Integer reasonCode;
    private String responseDescription;
    private ResultLevel level;

    public ResultResponse(ResultResponseStatus resultStatus) {
        this.responseCode = resultStatus.getResponseCode();
        this.reasonCode = resultStatus.getReasonCode();
        this.responseDescription = resultStatus.getDescription();
        this.level = getResultLevel(resultStatus);
    }

    public ResultResponse(ResultResponseStatus resultStatus, ResultLevel level) {
        this.responseCode = resultStatus.getResponseCode();
        this.reasonCode = resultStatus.getReasonCode();
        this.responseDescription = resultStatus.getDescription();
        this.level = level;
    }

    public ResultLevel getResultLevel(ResultResponseStatus resultStatus) {
        if (resultStatus == ResultResponseStatus.SUCCESS) {
            return ResultLevel.INFO;
        } else if (resultStatus == ResultResponseStatus.FAILURE) {
            return ResultLevel.BLOCKER;
        } else {
            return ResultLevel.WARN;
        }
    }
}
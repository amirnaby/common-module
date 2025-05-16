package com.niam.commonservice.logger;

public interface IMetadataWarningLog {
    void warningLogPublisher(String methodName, String trackCode, String externalTrackCode, String request, String description);
}
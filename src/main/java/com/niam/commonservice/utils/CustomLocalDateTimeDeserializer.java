package com.niam.commonservice.utils;

import com.niam.commonservice.exception.LocalDateTimeException;
import com.niam.commonservice.exception.ResultResponseStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private final DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            .toFormatter();

    private final DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            .toFormatter();


    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String dateString = jsonParser.getText();
        try {
            return LocalDateTime.parse(dateString, formatter1);
        } catch (Exception e1) {
            try {
                return LocalDateTime.parse(dateString, formatter2);
            } catch (Exception e2) {
                throw new LocalDateTimeException(
                        ResultResponseStatus.DATETIME_IS_NOT_OK.getResponseCode(),
                        ResultResponseStatus.DATETIME_IS_NOT_OK.getReasonCode(),
                        ResultResponseStatus.DATETIME_IS_NOT_OK.getDescription() + dateString
                );
            }
        }
    }
}
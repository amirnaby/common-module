package com.niam.commonservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertStringToDate {

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    };

    private ConvertStringToDate() {
    }

    public static LocalDateTime convertDate(String date) {
        return convertDate(date, LocalDateTime.now());
    }

    public static LocalDateTime convertDate(String date, LocalDateTime defaultDate) {
        if (date == null) {
            return defaultDate;
        }
        try {
            return LocalDateTime.parse(date, FORMATTERS[0]);
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(date, FORMATTERS[1]);
            } catch (Exception ex) {
                try {
                    return LocalDateTime.parse(date);
                } catch (Exception ex1) {
                    return defaultDate;
                }
            }
        }
    }
}

package com.niam.commonservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectConverter {
    private final ObjectMapper objectMapper;

    public String convertToJsonString(Object obj) {
        try {
            Class<?> requestClass = obj.getClass();
            Object data = requestClass.cast(obj);
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            return obj.toString();
        }
    }
}

package com.niam.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public Object sanitize(Object arg) {
        switch (arg) {
            case null -> {
                return null;
            }
            case Map<?, ?> map -> {
                Map<Object, Object> sanitized = new HashMap<>();
                map.forEach((k, v) -> {
                    if (k.toString().toLowerCase().contains("password")) {
                        sanitized.put(k, "***");
                    } else {
                        sanitized.put(k, sanitize(v));
                    }
                });
                return sanitized;
            }
            case String s -> {
                return s;
            }
            case Collection<?> col -> {
                return col.stream().map(this::sanitize).toList();
            }
            default -> {
            }
        }

        if (arg.getClass().isPrimitive() ||
                arg instanceof Number ||
                arg instanceof Boolean ||
                arg instanceof Character) {
            return arg;
        }

        if (arg.getClass().isArray()) {
            Object[] array = (Object[]) arg;
            Object[] sanitized = new Object[array.length];
            for (int i = 0; i < array.length; i++) sanitized[i] = sanitize(array[i]);
            return sanitized;
        }

        try {
            Object clone = arg.getClass().getDeclaredConstructor().newInstance();
            for (Field field : arg.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(arg);
                if (field.getName().toLowerCase().contains("password")) {
                    field.set(clone, "***");
                } else {
                    field.set(clone, sanitize(value));
                }
            }
            return clone;
        } catch (Exception e) {
            return arg;
        }
    }
}

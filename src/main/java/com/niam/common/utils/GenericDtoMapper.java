package com.niam.common.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class GenericDtoMapper {
    public static <T> void copyNonNullProperties(Object source, T target, String... ignoreFields) {
        List<String> ignoreList = Arrays.asList(ignoreFields);
        for (Field field : source.getClass().getDeclaredFields()) {
            if (ignoreList.contains(field.getName()))
                continue;
            try {
                field.setAccessible(true);
                Object value = field.get(source);
                if (value != null) {
                    Field targetField = null;
                    try {
                        targetField = target.getClass().getDeclaredField(field.getName());
                    } catch (NoSuchFieldException ignored) {}

                    if (targetField != null) {
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
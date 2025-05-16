package com.niam.commonservice.utils;

import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiValueMapConverter {
    public static Map<String, Object> convertToMap(MultiValueMap<String, Object> requestParams) {
        Map<String, Object> convertedParams = new HashMap<>();
        for (Map.Entry<String, List<Object>> entry : requestParams.entrySet()) {
            String key = entry.getKey();
            List<Object> values = entry.getValue();
            if (values.size() > 1) {
                convertedParams.put(key, new ArrayList<>(values));
            } else if (values.size() == 1 && !values.get(0).equals("")) {
                convertedParams.put(key, values.get(0));
            }
        }
        return convertedParams;
    }

    public static Map<String, Object> convertToStringMap(MultiValueMap<String, String> requestParams) {
        Map<String, Object> convertedParams = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : requestParams.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            if (values.size() > 1) {
                convertedParams.put(key, new ArrayList<>(values));
            } else if (values.size() == 1 && !values.get(0).isEmpty()) {
                convertedParams.put(key, values.get(0));
            }
        }
        return convertedParams;
    }
}
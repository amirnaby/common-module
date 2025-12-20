package com.niam.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class CommonConfigFile {
    @Value("${app.default-pagination.size:20}")
    private int defaultPageSize;
    @Value("${app.default-pagination.sort-field:id}")
    private String defaultPageSortField;
}
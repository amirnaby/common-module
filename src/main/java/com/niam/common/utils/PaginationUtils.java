package com.niam.common.utils;

import com.niam.common.config.CommonConfigFile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaginationUtils {
    private final CommonConfigFile configFile;

    public PageRequest pageHandler(Map<String, Object> requestParams) {
        Object pageObj = requestParams.remove("page");
        Object sizeObj = requestParams.remove("size");
        int pageNo = pageObj == null ? 0 : Integer.parseInt(pageObj.toString());
        int pageSize = sizeObj == null || Integer.parseInt(sizeObj.toString()) > configFile.getDefaultPageSize()
                ? configFile.getDefaultPageSize() : Integer.parseInt(sizeObj.toString());
        String pageSortField = requestParams.remove("pageSortField") == null
                ? configFile.getDefaultPageSortField() : requestParams.remove("pageSortField").toString();
        String pageSortType = requestParams.remove("pageSortType") == null
                ? "asc" : requestParams.remove("pageSortType").toString();

        return PageRequest.of(pageNo, pageSize, Sort.by(pageSortType.toUpperCase(), pageSortField));
    }
}
package com.java.demo2.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * FileName: DynamicDatasource
 * Author: yeyang
 * Date: 2018/4/2 15:43
 * 核心
 */
public class DynamicDatasource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatesourceTenantLocal.getDatasoucesName();
    }
}

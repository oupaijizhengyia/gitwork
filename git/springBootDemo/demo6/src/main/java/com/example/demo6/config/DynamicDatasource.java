package com.example.demo6.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * FileName: DynamicDatasourceTenantLocal
 * Author: yeyang
 * Date: 2018/7/10 11:28
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceTenantLocal.getTenant();
    }
}

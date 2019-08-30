package com.example.demo3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * FileName: DynamincDatasource
 * Author: yeyang
 * Date: 2018/4/8 10:09
 */
public class DynamincDatasource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceTenantLocal.getDatasource();
    }
}

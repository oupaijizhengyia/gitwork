package com.example.demo5.common.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * FileName: DynamicDatasource
 * Author: yeyang
 * Date: 2018/4/23 10:31
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceTenantLocal.getDatasourceName();
    }
}

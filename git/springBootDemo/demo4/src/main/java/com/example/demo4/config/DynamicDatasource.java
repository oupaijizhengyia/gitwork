package com.example.demo4.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * FileName: DynamicDatasource
 * Author: yeyang
 * Date: 2018/4/12 13:43
 * 核心,设置当前的
 * 数据源的名称
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceTenantLocal.getDatasourceName();
    }
}

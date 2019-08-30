package com.example.demo3.config;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: DynamicDatasourceTenantLocal
 * Author: yeyang
 * Date: 2018/4/8 10:10
 */
public class DynamicDatasourceTenantLocal {
    private static ThreadLocal<String> TENANT_LOCAL = new ThreadLocal<>();

    private static List<String> tenantList = new ArrayList<>();

    public static String getDatasource() {
        return TENANT_LOCAL.get();
    }

    public static void setDatasource(String datasource){
        TENANT_LOCAL.set(datasource);
    }

    public static void registerList(List<String> keyList){
        tenantList.addAll(keyList);
    }
    public static void register(String key){
        tenantList.add(key);
    }

}

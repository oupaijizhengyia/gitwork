package com.example.demo4.config;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: DynamicDatasourceTenantLocal
 * Author: yeyang
 * Date: 2018/4/12 13:44
 * 所有数据源列表
 */
public class DynamicDatasourceTenantLocal {
    private static ThreadLocal<String> TENANT_LOCAL = new ThreadLocal<>();

    private static List<String> datasourceNames = new ArrayList<>();

    public static String getDatasourceName(){
        return TENANT_LOCAL.get();
    }

    public static void setDatasourceName(String name){
        TENANT_LOCAL.set(name);
    }

    public static void registerTenant(String key){
        datasourceNames.add(key);
    }
    public static void registerTenantList(List<String> keys){
        datasourceNames.addAll(keys);
    }

    public static List<String> getDatasourceNames(){
        return datasourceNames;
    }
}

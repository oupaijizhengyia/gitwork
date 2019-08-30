package com.tangu.tangucore.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fenglei
 * @Date 10/27/17
 */

public class DynamicDataSourceTenantLocal {

    private static final ThreadLocal<String> TENANT_LOCAL = new ThreadLocal<String>();

    private static List<String> dataSourceNames = new ArrayList<>();

    public static void setDataSourceName(String dataSourceType){
        TENANT_LOCAL.set(dataSourceType);
    }

    public static String getDataSourceName(){
        return TENANT_LOCAL.get();
    }

    public static void clearDataSourceName(){
        TENANT_LOCAL.remove();
    }

    public static boolean containsDataSource(String dataSourceName){
        return dataSourceNames.contains(dataSourceName);
    }

    public static void registDataSource(String key){
        dataSourceNames.add(key);
    }

    public static List<String> getDataSourcesNames(){
        return dataSourceNames;
    }
}



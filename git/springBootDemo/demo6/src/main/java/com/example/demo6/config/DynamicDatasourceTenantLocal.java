package com.example.demo6.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * FileName: DynamicDatasourceTenantLocal
 * Author: yeyang
 * Date: 2018/7/10 11:29
 */
public class DynamicDatasourceTenantLocal {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static List<String> tenantList = new ArrayList<>(16);

    public static void setDatasource(String key) {
        threadLocal.set(key);
    }
    public static void register(String key){
        tenantList.add(key);
    }
    public static String getTenant(){
        return threadLocal.get();
    }
}

package com.java.demo2.config.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: DynamicDatesourceTenantLocal
 * Author: yeyang
 * Date: 2018/4/2 15:45
 * 本地账套表
 * 存在static区
 */

public class DynamicDatesourceTenantLocal {
    private static final ThreadLocal<String> TENANT_LOCAL = new ThreadLocal<>();

    private static List<String> datasouces = new ArrayList<>();

    public static void setDatasouces(String threadLocal){
        TENANT_LOCAL.set(threadLocal);
    }

    /**
     * 当前账套的数据源名称
     * @return
     */
    public static String getDatasoucesName(){
        return TENANT_LOCAL.get();
    }

    /**
     * 注册一个数据源
     * 即把账套加到激活账套列表
     * @param tenant
     */
    public void register(String tenant){
        datasouces.add(tenant);
    }

    /**
     * 获取所有数据源名称
     * @return
     */
    public static List<String> getDatasoucesNames(){
        return datasouces;
    }
}

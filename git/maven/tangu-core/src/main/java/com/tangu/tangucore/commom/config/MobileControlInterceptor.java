package com.tangu.tangucore.commom.config;

import com.tangu.common.exception.TanguException;
import com.tangu.tangucore.datasource.DynamicDataSourceTenantLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Fenglei on 2017/11/30.
 */
public class MobileControlInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenant=request.getParameter("code");
        if(tenant == null || tenant.length()==0){
        throw new TanguException("手持接口必须传账套名");
    }
        DynamicDataSourceTenantLocal.setDataSourceName(tenant);
        return super.preHandle(request, response, handler);
}
}

package com.tangu.tangucore.datasource;

import com.tangu.tangucore.security.jwt.JwtUserTool;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fenglei
 * @date 10/30/17
 */
@Component
public class DynamicDataSourceFilter extends OncePerRequestFilter {

    @Override
    @Order(100)//为了确保在JwtAuthenticationTokenFilter之后被调用（好像不加这行也行？）［Lei］
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String tenant =  JwtUserTool.getTenant();
        if(!"".equals(tenant) && tenant != null){
            DynamicDataSourceTenantLocal.setDataSourceName(tenant);
        }else{
//        	DynamicDataSourceTenantLocal.setDataSourceName("tcmts2");
        }
        
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}

package com.example.demo4.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: DynamicDatasourceConfig
 * Author: yeyang
 * Date: 2018/4/12 13:52
 */
@Configuration
@Slf4j
public class DynamicDatasourceConfig {
    @Value("${demo4.sysadmin}")
    private String sysadmin;

    @Autowired
    private Environment environment;
    private ConversionService conversionService = new DefaultConversionService();
    private DataSource defaultDatasource;
    private PropertyValues propertyValues;

    @Bean
    @ConditionalOnProperty(value = "sysadmin",prefix = "demo4")
    public DataSource createDatesource(){
        DynamicDatasource re = new DynamicDatasource();
        Map tagertDatasource = new HashMap(16);
        /*
        建立管理员的主数据源
         */
        defaultDatasource = biuldDatasource(sysadmin);
        DynamicDatasourceTenantLocal.registerTenant(sysadmin);
        DynamicDatasourceTenantLocal.setDatasourceName(sysadmin);
        tagertDatasource.put(sysadmin,defaultDatasource);

        /*
        来宾数据源
         */
        Map comsumerDatasource = new HashMap(16);
        List<String> tenantList = getTenantList();
        DynamicDatasourceTenantLocal.registerTenantList(tenantList);
        tenantList.forEach(t->{
            comsumerDatasource.put(t,biuldDatasource(t));
        });
        /*
        加入目标数据源
        并把所有目标数据源
        放入核心的DynamicDatasource
        的目标数据源中
         */
        tagertDatasource.putAll(comsumerDatasource);
        re.setTargetDataSources(tagertDatasource);
        return re;
    }

    private DataSource biuldDatasource(String sysadmin) {
        DataSource re = null;
        Class<? extends DataSource> datasourceType;
        RelaxedPropertyResolver rpp = new RelaxedPropertyResolver(environment,"spring.datasource.");
        try {
            datasourceType = (Class<? extends DataSource>)Class.forName(rpp.getProperty("type"));
            re = DataSourceBuilder.create()
                    .type(datasourceType)
                    .username(rpp.getProperty("username"))
                    .password(rpp.getProperty("password"))
                    .driverClassName("driverClassName")
                    .url(MessageFormat.format(rpp.getProperty("url"),sysadmin))
                    .build();
            bindDatasource(environment,re);

        } catch (ClassNotFoundException e) {
            log.info("mysql驱动找不到",e);
        }
        return re;
    }

    /**
     * 绑定其他属性
     * @param re
     */
    private void bindDatasource(Environment environment,DataSource re) {
        RelaxedDataBinder r = new RelaxedDataBinder(re);
        r.setIgnoreNestedProperties(false);
        r.setIgnoreUnknownFields(true);
        r.setConversionService(conversionService);
        r.setIgnoreUnknownFields(false);
        if(propertyValues == null){
            Map rpps = new RelaxedPropertyResolver(environment,"spring.datasource").getSubProperties(".");
            HashMap rpp = new HashMap(rpps);//如果直接在里面remove会报不支持的操作的错误
            rpp.remove("url");
            rpp.remove("driverClassName");
            rpp.remove("username");
            rpp.remove("password");
            rpp.remove("type");
            propertyValues = new MutablePropertyValues(rpp);
        }
        r.bind(propertyValues);
    }


    public List<String> getTenantList() {
        List<String> re = new ArrayList<>(16);
        Connection conn = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;
        String sql = "select company_code from company";
        try {
            conn = defaultDatasource.getConnection();
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()){
                String code = rs.getString("company_code");
                re.add(code);
            }
        } catch (SQLException e) {
            log.info("sql语法错误",e);
        }finally {
            CloseAll(conn,ppst,rs);
        }
        return re;
    }

    private void CloseAll(Connection conn, PreparedStatement ppst,ResultSet re) {
                try {
                    if(conn != null) {
                        conn.close();
                    }
                    if(ppst != null){
                        ppst.close();
                    }
                    if(re != null){
                        re.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }
}

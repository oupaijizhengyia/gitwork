package com.example.demo6.config;

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
 * Date: 2018/7/10 11:35
 */
@Configuration
@Slf4j
public class DynamicDatasourceConfig {
    @Value("${demo6.sysadmin}")
    private String sysadmin;
    private DataSource defaultDatsource;
    private ConversionService conversionService = new DefaultConversionService();
    @Autowired
    private Environment environment;
    private PropertyValues propertyValues;

    @Bean
    @ConditionalOnProperty(prefix = "demo6.", value = "sysadmin")
    public DataSource getDataSource(){
        DynamicDatasource dynamicDatasource = new DynamicDatasource();
        Map<Object,Object> targetSource = new HashMap<>(16);
        defaultDatsource = buildDatasource(sysadmin);
        targetSource.put(sysadmin,defaultDatsource);
        DynamicDatasourceTenantLocal.register(sysadmin);
        DynamicDatasourceTenantLocal.setDatasource(sysadmin);
        //来宾数据源
        List<String> tenants = getTenant();
        for (String s: tenants
             ) {
            targetSource.put(s,buildDatasource(s));
            DynamicDatasourceTenantLocal.register(s);
        }
        dynamicDatasource.setTargetDataSources(targetSource);
        return dynamicDatasource;
    }

    /**
     * 装配数据源
     * @param tenant 账套名称
     * @return
     */
    private DataSource buildDatasource(String tenant) {
        DataSource re = null;
        Class<? extends DataSource> type;
        RelaxedPropertyResolver rpp = new RelaxedPropertyResolver(environment,"spring.datasource.");
        try {
            type = (Class<? extends DataSource>)Class.forName(rpp.getProperty("type"));
            DataSourceBuilder dsb = DataSourceBuilder.create()
                    .driverClassName(rpp.getProperty("driverClassName"))
                    .username(rpp.getProperty("username"))
                    .password(rpp.getProperty("password"))
                    .type(type)
                    .url(MessageFormat.format(rpp.getProperty("url"),tenant));
            re = dsb.build();
            blindData(re,environment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 绑定参数
     * @param re
     * @param environment
     */
    private void blindData(DataSource re, Environment environment) {
        RelaxedDataBinder relaxedDataBinder = new RelaxedDataBinder(re);
        relaxedDataBinder.setIgnoreNestedProperties(false);
        relaxedDataBinder.setIgnoreUnknownFields(true);
        relaxedDataBinder.setIgnoreInvalidFields(false);
        relaxedDataBinder.setConversionService(conversionService);
        if(propertyValues == null){
            /*
            这里设置的是数据库连接池的属性
            因为所有的数据源的数据库连接池相同,属性相同
            故而,当且仅当连接池属性为空的时候设置属性
            实则这里用到的为单例模式
            若每个数据源的数据库连接池属性不同,是否要设置多个呢? [ye]
             */
            Map<String,Object> rpp = new RelaxedPropertyResolver(environment,"spring.datasource").getSubProperties(".");
            Map<String,Object> values = new HashMap<>(rpp);
            values.remove("type");
            values.remove("driverClassName");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            propertyValues = new MutablePropertyValues(values);
        }
        relaxedDataBinder.bind(propertyValues);
    }

    private List<String> getTenant() {
        List<String> re= new ArrayList<>(16);
        Connection conn = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;
        try {
            conn = defaultDatsource.getConnection();
            String sql  = "select company_code from company where version_type = 'tcmts2'";
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()){
                String code = rs.getString("company_code");
                re.add(code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,ppst,rs);
        }
        return re;
    }

    private void close(Connection conn, PreparedStatement ppst, ResultSet rs) {
        try {
            if(conn != null){
                conn.close();
            }
            if(ppst != null){
                ppst.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}

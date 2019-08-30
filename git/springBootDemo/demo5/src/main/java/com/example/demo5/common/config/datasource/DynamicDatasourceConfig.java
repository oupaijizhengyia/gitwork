package com.example.demo5.common.config.datasource;

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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: DynamicDatasourceConfig
 * Author: yeyang
 * Date: 2018/4/23 10:47
 */
@Configuration
@Slf4j
public class DynamicDatasourceConfig {

    private DataSource defaultDatasource;
    @Value("${demo5.sysadmin}")
    private String sysadmin;
    @Autowired
    private Environment environment;
    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues propertyValues;

    @Bean
    @ConditionalOnProperty(value = "sysadmin",prefix = "demo5")
    public DataSource createDatasource(){
        DynamicDatasource targetDatasource = new DynamicDatasource();
        /*
        创建主数据源
         */
        Map targetMap = new HashMap(16);
        defaultDatasource = buildDatasource(sysadmin);
        targetMap.put(sysadmin,defaultDatasource);
        DynamicDatasourceTenantLocal.register(sysadmin);
        DynamicDatasourceTenantLocal.setDatasourceName(sysadmin);

        /*
        来宾数据源
         */
        Map<String,Object> consumerDatasource = new HashMap<>(16);
        List<String> tenantList = getTenantList();
        tenantList.forEach(t->{
            consumerDatasource.put(t,buildDatasource(t));
        });
        DynamicDatasourceTenantLocal.registerTenantList(tenantList);
        targetMap.putAll(consumerDatasource);
        targetDatasource.setTargetDataSources(targetMap);
        return targetDatasource;
    }

    private List<String> getTenantList(){
        List<String> re = new ArrayList<>(16);
        Connection conn = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;
        try {
            conn = defaultDatasource.getConnection();
            String sql = "select company_code from company";
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()){
                re.add(rs.getString("company_code"));
            }
        }catch (Exception e){
            log.info("sql语句错误");
        }finally {
            closeAll(conn,ppst,rs);
        }
        return re;
    }

    private void closeAll(Connection conn, PreparedStatement ppst, ResultSet rs) {
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
            log.info("驱动为空",e);
        }
    }

    private DataSource buildDatasource(String sysadmin) {
        DataSource dataSource = null;
        Class<? extends DataSource> datasourceType;
        RelaxedPropertyResolver rpp = new RelaxedPropertyResolver(environment,"spring.datasource.");
        try {
            datasourceType = (Class<? extends DataSource>)Class.forName(rpp.getProperty("type"));
            dataSource = DataSourceBuilder.create()
                    .type(datasourceType)
                    .url(MessageFormat.format(rpp.getProperty("url"),sysadmin))
                    .driverClassName(rpp.getProperty("driverClassName"))
                    .username(rpp.getProperty("username"))
                    .password(rpp.getProperty("password"))
                    .build();
        } catch (ClassNotFoundException e) {
            log.info("mysql驱动找不到",e);
        }
        bindDatasource(environment,dataSource);
        return dataSource;
    }

    private void bindDatasource(Environment environment, DataSource dataSource) {
        RelaxedDataBinder rdb = new RelaxedDataBinder(dataSource);
        rdb.setIgnoreUnknownFields(true);
        rdb.setIgnoreNestedProperties(false);
        rdb.setIgnoreInvalidFields(false);
        rdb.setConversionService(conversionService);
        if(propertyValues == null){
            Map rpps = new RelaxedPropertyResolver(environment,"spring.datasource").getSubProperties(".");
            Map rpp = new HashMap(rpps);
            rpp.remove("url");
            rpp.remove("password");
            rpp.remove("username");
            rpp.remove("driverClassName");
            rpp.remove("type");
            propertyValues = new MutablePropertyValues(rpp);
        }
        rdb.bind(propertyValues);
    }

}

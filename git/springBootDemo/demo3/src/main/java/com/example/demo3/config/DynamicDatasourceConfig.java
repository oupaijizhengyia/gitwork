package com.example.demo3.config;

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
 * Date: 2018/4/8 10:36
 */
@Configuration
@Slf4j
public class DynamicDatasourceConfig {

    @Value("${demo3.sysadmin}")
    private String sysadmin;
    @Autowired
    private Environment environment;
    private PropertyValues propertyValues;
    private DataSource defaultDatasource;
    private ConversionService conversionService = new DefaultConversionService();


    @Bean
    @ConditionalOnProperty(prefix = "demo3.",value = "sysadmin")
    public DataSource createDatasource(){
        System.out.println("*****************");
        DynamincDatasource re = new DynamincDatasource();
        Map<Object, Object> tagetMap = new HashMap<>(16);


        /*
        管理员
         */
        defaultDatasource = biuldDatasource(sysadmin);
        DynamicDatasourceTenantLocal.setDatasource(sysadmin);
        DynamicDatasourceTenantLocal.register(sysadmin);
        tagetMap.put(sysadmin,defaultDatasource);


        List<String> tenantList = getTenantList();
        tenantList.forEach(t->{
            tagetMap.put(t,biuldDatasource(t));
        });

        System.out.println("+++++++++++++++++++0+"+tagetMap.size());
        DynamicDatasourceTenantLocal.registerList(tenantList);
        re.setTargetDataSources(tagetMap);
        return re;
    }

    /**
     * 在系统管理员的库中
     * 获取所有公司的的账套列表
     * @return
     */
    private List<String> getTenantList() {
        List<String> re = new ArrayList<>();

        PreparedStatement ppst = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = "select company_code from company";
        try {
            conn = defaultDatasource.getConnection();
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()){
                String tenant = rs.getString(1);
                re.add(tenant);
            }
        } catch (SQLException e) {
            log.info("sql语句错误",e);
        }
        closeAll(conn,ppst,rs);
        return re;
    }

    private void closeAll(Connection conn, PreparedStatement ppst, ResultSet rs) {
        try {
            if(conn != null){
                conn.close();
            }
            if (ppst != null){
                ppst.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private DataSource biuldDatasource(String key){
        DataSource re = null;

        RelaxedPropertyResolver rpp = new RelaxedPropertyResolver(environment,"spring.datasource.");
        Class<? extends DataSource> datasourceType = null;
        try {
            datasourceType = (Class<? extends DataSource>)Class.forName(rpp.getProperty("type"));
            DataSourceBuilder db = DataSourceBuilder.create()
                    .driverClassName(rpp.getProperty("driverClassName"))
                    .password(rpp.getProperty("password"))
                    .username(rpp.getProperty("username"))
                    .url(MessageFormat.format(rpp.getProperty("url"),key))//这里就是设置url  设置占位符的库的名称
                    .type(datasourceType);
            re = db.build();
            bindDatasource(environment,re);
        } catch (ClassNotFoundException e) {
            log.info("数据源驱动找不到",e);
        }
        return re;
    }

    /**
     * 绑定数据源
     * @param environment
     * @param re
     */
    private void bindDatasource(Environment environment, DataSource re) {
        RelaxedDataBinder relaxedDataBinder = new RelaxedDataBinder(re);
        relaxedDataBinder.setIgnoreInvalidFields(false);
        relaxedDataBinder.setIgnoreUnknownFields(true);
        relaxedDataBinder.setIgnoreNestedProperties(false);
        relaxedDataBinder.setConversionService(conversionService);
        if(propertyValues == null){
            Map<String,Object> rpps = new RelaxedPropertyResolver(environment,"spring.datasource").getSubProperties(".");
            HashMap<String,Object> rpp =new HashMap<>(rpps);
            rpp.remove("username");
            rpp.remove("password");
            rpp.remove("url");
            rpp.remove("driverClassName");
            rpp.remove("type");
            propertyValues = new MutablePropertyValues(rpp);
        }
        relaxedDataBinder.bind(propertyValues);
    }


}

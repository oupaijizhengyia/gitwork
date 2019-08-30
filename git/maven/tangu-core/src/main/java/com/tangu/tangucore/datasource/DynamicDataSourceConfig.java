package com.tangu.tangucore.datasource;

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
 * @author fenglei
 * @date 10/27/17
 */

@Slf4j
@Configuration
public class DynamicDataSourceConfig {
    @Value("${tangu.datasource.sysadmin}")
    private String sysadmin;

    @Value("${tangu.datasource.versiontype:tcmts}")
    private String versiontype;

    @Autowired
    private Environment env;

    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;
    private DataSource defaultDataSource;


    @Bean
    @ConditionalOnProperty(prefix = "tangu.datasource.", value = "sysadmin")
    public DataSource createDynamicDataSource(){
        defaultDataSource = buildDataSource(sysadmin);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object,Object> targetDataSources = new HashMap<>(16);
        targetDataSources.put(sysadmin, defaultDataSource);
        DynamicDataSourceTenantLocal.registDataSource(sysadmin);

        HashMap<String, DataSource> customDataSources = buildAvaiableDataSourceMap();
        targetDataSources.putAll(customDataSources);
        for(String key: customDataSources.keySet()){
            DynamicDataSourceTenantLocal.registDataSource(key);
        }

        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;

    }

    private HashMap<String, DataSource> buildAvaiableDataSourceMap(){
        List<String> tenantList = getTenantList();
        HashMap<String, DataSource> map = new HashMap<>(16);
        for(String tenant : tenantList){
            map.put(tenant,buildDataSource(tenant));
        }
        return map;
    }
    
    private  DataSource buildDataSource(String tenant){

        DataSource dataSource = null;
        Class<? extends  DataSource> dataSourceType;
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env,"spring.datasource.");
        try{
            dataSourceType = (Class<? extends DataSource>)Class.forName(propertyResolver.getProperty("type"));
            DataSourceBuilder factory = DataSourceBuilder.create()
                    .driverClassName(propertyResolver.getProperty("driverClassName"))
                    .url(MessageFormat.format(propertyResolver.getProperty("url"),tenant))
                    .username(propertyResolver.getProperty("username"))
                    .password(propertyResolver.getProperty("password"))
                    .type(dataSourceType);
            dataSource = factory.build();
            bindData(dataSource,env);
        }catch (ClassNotFoundException e){
            log.error("找不到数据库驱动",e);
        }
        return dataSource;
    }

    private  void bindData(DataSource dataSource, Environment env){
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);
        if(dataSourcePropertyValues == null){
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
            Map<String, Object> values = new HashMap<>(rpr);
            values.remove("type");
            values.remove("driverClassName");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

    /**
     * 从sysadmin库里找出激活的账套
     * @return 账套list
     */
    private List<String> getTenantList(){
        List<String> tenantList = new ArrayList<>();
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet resultSet = null;
        String tenant;
        try {
            conn = defaultDataSource.getConnection();
            String sql = "select company_code,transcompany_code,exchange_code,exchange_password from company where version_type=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,versiontype);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                tenant = resultSet.getString("company_code");
                tenantList.add(tenant);
            }
        } catch (SQLException e) {
            log.error("sql错误", e);
        } finally {
            closeAll(resultSet,pstmt,conn);
        }
        return tenantList;
    }


    private static void closeAll(ResultSet rst,PreparedStatement pstmt,Connection conn)
    {
        try
        {
            if (rst != null)
            {
                rst.close();
                rst = null;
            }
            if(pstmt!=null){
                pstmt.close();
                pstmt=null;
            }
            if(conn!=null){
                conn.close();
                conn=null;
            }
        }
        catch (SQLException e)
        {
            log.error(e.getMessage(), e);
        }
    }
}

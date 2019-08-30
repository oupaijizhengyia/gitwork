package com.java.demo2.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * FileName: DynamicDatasouceConfig
 * Author: yeyang
 * Date: 2018/4/2 15:58
 */
@Configuration
@Slf4j
public class DynamicDatasouceConfig {

    /*
    这里的sysadmin其实是这个版本的系统数据库的名称
    其他的说不行不是了 所以配置在属性文件里
     */

    @Value("${demo2.datasource.sysadmin}")
    private String sysadmin;

    @Autowired
    private  Environment environment;

    private DataSource defaultDataSource;
    private PropertyValues propertyValues;


    @Bean
    public DataSource createDynamicDataSource(){
        /*
        使用jdbc获取sysadmin数据库下面的
        所有账套
            1.设置默认数据源,不然jdbc不知道去哪里查询
         */
        defaultDataSource = buildDataSource(sysadmin);
        DynamicDatasource dynamicDatasource = new DynamicDatasource();

        /*
        先把系统数据源放到账套列表
         */
        Map<Object,Object> targetDataSource = new HashMap<>(16);
        targetDataSource.put(sysadmin,defaultDataSource);
        DynamicDatesourceTenantLocal.setDatasouces(sysadmin);
        /*
        使用jdbc获取sysadmin中的账套列表
         */
        List<String> tenantList = getTenantList();
        tenantList.forEach(o->{
            targetDataSource.put(o,buildDataSource(o));
        });
        for (Object key :targetDataSource.keySet()){
            DynamicDatesourceTenantLocal.setDatasouces(String.valueOf(key));
        }

        /*
        这里是把所有的数据源
        设置进目标数据源(关键一步)
         */
        dynamicDatasource.setTargetDataSources(targetDataSource);
        return dynamicDatasource;
    }


    private DataSource buildDataSource(String sysadmin) {
        /*
        这里的参数主要是绑定数据源的url
        这里设置到属性文件中的那个占位符中
        如果是要不同端口,不同链接的数据库
        还要设置(猜测)


        本来这个dasourceType
        里面应该是 阿里的那个数据库连接池的 .class
        现在是从配置文件中取的
         */

        DataSource re = null;
        Class<? extends DataSource> datasourceType;
        RelaxedPropertyResolver rpp = new RelaxedPropertyResolver(environment,"spring.datasource.");
        try {
            //数据库链接池种类
           datasourceType = (Class<? extends DataSource>)Class.forName(rpp.getProperty("type"));
            //数据库链接的其他属性
           DataSourceBuilder factory = DataSourceBuilder.create()
                    .driverClassName(rpp.getProperty("driverClassName"))
                    .url(MessageFormat.format(rpp.getProperty("url"),sysadmin))
                    .password(rpp.getProperty("password"))
                    .username(rpp.getProperty("username"))
                    .type(datasourceType);
            re = factory.build();
            bindDataSource(environment,re);
        }catch (ClassNotFoundException e){
            log.info("数据源驱动不存在",e);
        }
        return re;
    }

    /**
     * 绑定数据源
     * 再此同时设置
     * 设计到spring源码
     * 目前暂时先知其然
     * 等到回头读懂源码之后再注释
     * @param environment
     * @param re
     */
    private void bindDataSource(Environment environment, DataSource re) {
        RelaxedDataBinder relaxedDataBinder = new RelaxedDataBinder(re);
        relaxedDataBinder.setConversionService(new DefaultConversionService());
        relaxedDataBinder.setIgnoreNestedProperties(false);
        relaxedDataBinder.setIgnoreInvalidFields(false);
        relaxedDataBinder.setIgnoreUnknownFields(true);
        if(propertyValues == null){
            Map<String, Object> rpr = new RelaxedPropertyResolver(environment,"spring.datasource").getSubProperties(".");
            Map<String, Object> values = new HashMap<>(rpr);
            values.remove("type");
            values.remove("url");
            values.remove("driverClassName");
            values.remove("username");
            values.remove("password");
            propertyValues = new MutablePropertyValues(values);
        }
        relaxedDataBinder.bind(propertyValues);
    }

    private List<String> getTenantList() {
        List<String> re = new ArrayList<>();
        PreparedStatement ppst = null;
        Connection con = null;
        ResultSet resultSet = null;

        try {
            con = defaultDataSource.getConnection();
            String sql = "select company_code from company";
            ppst = con.prepareStatement(sql);
            resultSet = ppst.executeQuery();
            while (resultSet.next()){
                String tenant = resultSet.getString(1);
                re.add(tenant);
            }
        } catch (SQLException e) {
            log.info("sql语法错误",e);
        }finally {
            closeAll(resultSet,ppst,con);
        }

        return re;
    }

    private void closeAll(ResultSet rst,PreparedStatement pstmt,Connection conn) {
        try {
            if (rst != null){
                rst.close();
            }
            if (pstmt != null){
                pstmt.close();
            }
            if (conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

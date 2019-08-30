package com.tangu.tangucore.commom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author fenglei on 10/25/17.
 * 为了加入IntervalLogInterceptor，监控每个controll方法的执行时间
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${tangu.enableIntervalLogInterceptor}")
    private Boolean enableIntervalLogInterceptor;
    
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//      return new MappingJackson2HttpMessageConverter() {
//
//        @Override
//        protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws HttpMessageNotWritableException, IOException{
//          if(!(object instanceof ResponseModel)){
//            object = new ResponseModel(object);
//          }
//          super.writeInternal(object, type, outputMessage);
//        }
//        
//      };
//    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        if(enableIntervalLogInterceptor){
            registry.addInterceptor(new IntervalLogInterceptor()).addPathPatterns("/**");
        }

        registry.addInterceptor(new MobileControlInterceptor()).addPathPatterns("/MobileHandle");

        super.addInterceptors(registry);
    }
    
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//      converters.add (mappingJackson2HttpMessageConverter());
//      super.configureMessageConverters (converters);
//    }
}

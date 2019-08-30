package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * FileName: SwaggerConfig
 * Author: yeyang
 * Date: 2018/3/28 10:30
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createDockApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("第一个demo")
                        .description("简直狗带,搞半天才出来")
                        .build())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .build();
        return docket;
    }

}

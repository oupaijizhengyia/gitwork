package com.example.demo5.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * FileName: SwaggerConfig
 * Author: yeyang
 * Date: 2018/4/23 10:25
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("demo5")
                        .description("含有jwt")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo5.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }








}

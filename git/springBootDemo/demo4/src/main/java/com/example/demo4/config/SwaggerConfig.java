package com.example.demo4.config;

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
 * Date: 2018/4/12 13:31
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket creterDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                    .title("demo4")
                    .description("第四个背着写")
                    .build())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example.demo4.controller"))
                .build();
    }
}

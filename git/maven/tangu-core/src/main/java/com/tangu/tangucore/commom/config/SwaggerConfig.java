package com.tangu.tangucore.commom.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * 通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2。
 * 再通过createRestApi函数创建Docket的Bean之后， apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
 * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
 * 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API， 并产生文档内容（除了被@ApiIgnore指定的请求）。
 *
 * 添加文档内容
 *
 * 在完成了上述配置后，其实已经可以生产文档内容，但是这样的文档主要针对请求本身，
 * 而描述主要来源于函数等命名产生，对用户并不友好，我们通常需要自己增加一些说明来丰富文档内容。
 * 如:我们通过@ApiOperation注解来给API增加说明、
 * 通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
 * @author fenglei on 8/29/17.
 */

/**
 * @author fenglei on 10/25/17.
 * Swagger配置
 */
@SuppressWarnings("ALL")
@Configuration
@EnableSwagger2
//@Profile("dev")
public class SwaggerConfig {

    @Value("${jwt.header}")
    private String jwtHeader;

    // TODO http://localhost:8080/{context-path}/swagger-ui.html

    /**
     * Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。
     * 总体目标是使客户端和文件系统作为服务器以同样的速度来更新。 文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。
     * Swagger 让部署管理和使用功能强大的API从未如此简单。
     *
     * 前后端分离开发 API文档非常明确 测试的时候不需要再使用URL输入浏览器的方式来访问Controller
     * 传统的输入URL的测试方式对于post请求的传参比较麻烦（当然，可以使用postman这样的浏览器插件）
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tangu"))
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    SecurityConfiguration security() {
//        return new SecurityConfiguration(null, null, null, null, "Bearer access_token", ApiKeyVehicle.HEADER, "Authorization", ",");
//    }


    private ApiInfo apiInfo() {
        Contact contact = new Contact("冯磊", "https://swagger.io", "fenglei@tangusoft.com");
        return new ApiInfoBuilder()
                .title("接口内容更新后请及时更新文档，如果前端开发同学发现API不对请及时与后端开发沟通")
                .description("测试token: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTIiLCJhdWQiOiJ3ZWIiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFXzI4In1dLCJleHAiOjE1MTYyNjEzNDAsImlhdCI6MTUxNTY1NjU0MCwidGVuYW50IjoidGNtdHMyIiwianRpIjoiMjQifQ.GcDvfabF3NreDet7Yow6quT8-3KgOJZkrYPEhgJJZs9m4RkfXZxmgfCK8V2w3FZ5v53fIcwfPgIKUHUqhFqftA\n")
//                .termsOfServiceUrl("https://github.com/timebusker/spring-boot/tree/master/spring-boot-12-Swagger2/")
//                .contact(contact)
//                .version("2.0.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    // these components have been copied from
    // http://springfox.github.io/springfox/docs/current/#getting-started
    // in an attempt to configure authToken in the header while making the api calls
    // but it is still not working
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(regex("/rest/.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("mykey", authorizationScopes));
    }

}
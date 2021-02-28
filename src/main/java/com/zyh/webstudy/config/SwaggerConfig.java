package com.zyh.webstudy.config;

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

import java.util.ArrayList;
import java.util.List;

@Configuration//配置类
@EnableSwagger2 // swagger注解
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zyh.webstudy.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts()) /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("web学习交流平台接口文档")
                .description("本文档描述了web学习交流平台服务接口定义")
                .contact(new Contact("zyh","http://zouyh.club","898362059@qq.com"))
                .version("1.0")
                .build();
    }

    /* 设置请求头信息 */
    private List<ApiKey> securitySchemes(){
        List<ApiKey> objects = new ArrayList<>();
        objects.add(new ApiKey("token", "token", "header"));
        return objects;
    }
    /* 设置需要认证的路径 */
    public List<SecurityContext> securityContexts(){
        List<SecurityContext> results = new ArrayList<>();
        results.add(securityContextByPath("^(?!auth).*$"));
        return results;

    }
    private SecurityContext securityContextByPath(String pathRegex) {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }
    private List<SecurityReference> defaultAuth() {

        /* 授权范围 */
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        authorizationScopes[0] = authorizationScope;

        List<SecurityReference> references = new ArrayList<>();
        SecurityReference authorization = new SecurityReference("token", authorizationScopes);
        references.add(authorization);
        return references;
    }

}

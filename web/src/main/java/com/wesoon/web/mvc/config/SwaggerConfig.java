package com.wesoon.web.mvc.config;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description
 * @Author wangpeng
 * @Date 2020/4/22 09:32
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api() {

        //添加请求头token参数
        List<Parameter> pars=new ArrayList<>();
        ParameterBuilder parameterBuilder=new ParameterBuilder();
        Parameter tokenPar= parameterBuilder.name("Authorization").description("令牌")
                .modelRef(new ModelRef("String")).parameterType("header")
                .required(false).build();
        pars.add(tokenPar);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SaaS2 APIs")
                .version("1.0")
                .build()
                ;
    }
}

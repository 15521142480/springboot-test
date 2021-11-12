package com.lai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger2 接口配置管理
 *
 * @author laixueshi
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

//    @Bean
//    public Docket createRestApi() {
//        //添加head参数配置start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name("token").description("请求头的令牌token").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue("DSuF5Mqpz3bGDQM9GkFmI2mcMBG6QbA5").build();
//        pars.add(tokenPar.build());
//        //添加head参数配置end
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//        // 为当前包路径
//                .apis(RequestHandlerSelectors.basePackage("com.sunshine.controller")).paths(PathSelectors.any())
//                .build();
////                .build().globalOperationParameters(pars);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("我要生活后端接口api")
//                .description("我要生活后端接口api接口说明")
//                .version("v1.0.0")
//                .build();


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lai.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("我要音乐接口api")
                        .description("我要音乐接口api接口说明")
                        .version("9.0")
                        .contact(new Contact("这是邮箱","blog.csdn.net","15521142480@gmail.com"))
                        .license("这是跳网址")
                        .licenseUrl("http://www.baidu.com")
                        .build());

    }
}
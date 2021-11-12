package com.lai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <p> 全局配置解决跨域 </p>
 *
 * @author : laixueshi
 * @description :
 * @date : 2020/11/22
 */
@Configuration
public class CorsConfig {

    private CorsConfiguration config() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        // ① 设置你要允许的网站域名，如果全允许则设为 *
        corsConfiguration.addAllowedOrigin("*");
        // corsConfiguration.addAllowedOrigin("http://www.laixueshi.com");
        // ② 如果要限制 HEADER 或 METHOD 请自行更改
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config());
        return new CorsFilter(source);
    }

//    @Bean
//    public FilterRegistrationBean corsFilter2() {
//        FilterRegistrationBean bean = new FilterRegistrationBean( corsFilter() );
//        // 这个顺序很重要哦，为避免麻烦请设置在最前
//        bean.setOrder(0);
//        return bean;
//    }

}


// ------------------ 方法二 ----------------------
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // /**: 表示本应用的所有方法都会去处理跨域请求
//        registry.addMapping("/**")
//                // ① 设置你要允许的网站域名，如果全允许则设为 *
////                .allowedOrigins("http://127.0.0.1:8080")
//                .allowedOrigins("*")
//                // ② allowedMethods表示允许通过的请求数
//                .allowedMethods("*")
//                // ③ allowedHeaders则表示允许的请求头
//                .allowedHeaders("*");
//    }

//        addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
//        allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
//        allowedMethods：允许输入参数的请求方法访问该跨域资源服务器，如：POST、GET、PUT、OPTIONS、DELETE等。
//        allowCredentials： 响应头表示是否可以将对请求的响应暴露给页面。返回true则可以，其他值均不可以
//        allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息，如："X-YAUTH-TOKEN"
//        maxAge：配置客户端缓存预检请求的响应的时间（以秒为单位）。默认设置为1800秒（30分钟）

//}


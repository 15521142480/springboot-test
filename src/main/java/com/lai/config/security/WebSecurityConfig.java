package com.lai.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @Description: SpringSecurity安全框架配置
 * @Author: laixues
 * @Date: 2020/12/29
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 配置拦截请求资源
     * @param http：HTTP请求安全处理
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin() // 表单方式
//        //http.httpBasic()  //HTTP Basic认证方式 (浏览器内置验证框)
//                .and()
//                .authorizeRequests()  // 授权配置
//                .anyRequest()  // 所有请求
//                .authenticated(); // 都需要认证
//    }

    /**
     * 配置拦截请求资源2
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/mobile/**").permitAll() //配置需要忽略的请求
                .antMatchers("/mobile/**").authenticated()
                .antMatchers("/upload/**")
                .permitAll()
                .and().logout().permitAll(); // 配置不需要登录验证
    }


    /**
     * 全局配置
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        // 全局配置：忽略url
        web.ignoring().antMatchers("/test/**");
        web.ignoring().antMatchers("/mobile/**");
        web.ignoring().antMatchers("/upload/**");
    }

}

package com.ty.show.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 前后端分离必要配置
 * 跨域请求
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 允许所有url请求
                .allowedOrigins("*") // 允许所有地址请求
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")// 允许所有请求方式
                .maxAge(3600)// 生命周期
                .allowCredentials(true);// 允许携带cookies
    }
}

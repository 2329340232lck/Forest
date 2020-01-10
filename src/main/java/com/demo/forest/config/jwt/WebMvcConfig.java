package com.demo.forest.config.jwt;//package cn.config.jwt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private TokenInterceptors tokenInterceptors;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptors)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/static/**","/user/userLogin.ajax", "/public/login.html");
//    }
//}

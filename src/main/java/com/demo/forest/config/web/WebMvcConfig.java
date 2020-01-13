package com.demo.forest.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private ForestInterceptors forestInterceptors;

    private List<String> excludePaths = Arrays.asList("/login.html", "/user/login.ajax", "/css/**", "/img/**", "/js/**", "/libs/**");

    public WebMvcConfig(ForestInterceptors forestInterceptors) {
        this.forestInterceptors = forestInterceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(forestInterceptors);
        registration.addPathPatterns("/**")
                .excludePathPatterns(excludePaths);
    }
}

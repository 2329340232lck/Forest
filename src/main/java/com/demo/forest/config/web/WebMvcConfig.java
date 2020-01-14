package com.demo.forest.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private ForestProperty forestProperty;

    private ForestInterceptors forestInterceptors;

    public WebMvcConfig(ForestProperty forestProperty, ForestInterceptors forestInterceptors) {
        this.forestProperty = forestProperty;
        this.forestInterceptors = forestInterceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(forestInterceptors);
        registration.addPathPatterns(forestProperty.getResourcePaths())
                .excludePathPatterns(forestProperty.getExcludePaths());
    }
}

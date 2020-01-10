package com.demo.forest.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private ForestInterceptors forestInterceptors;

    public WebMvcConfig(ForestInterceptors forestInterceptors) {
        this.forestInterceptors = forestInterceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(forestInterceptors);
        registration.addPathPatterns("/**");
    }
}

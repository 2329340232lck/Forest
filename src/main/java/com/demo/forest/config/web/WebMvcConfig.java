package com.demo.forest.config.web;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedHashMap;
import java.util.Map;

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

    //====================================以下为Shiro配置==============================================

    @Bean
    public SecurityManager securityManager(CustomRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        //设置登录成功跳转的URL
        factoryBean.setSuccessUrl("/index.html");
        //设置登录URL
        factoryBean.setLoginUrl("/login.html");
        Map<String, String> map = new LinkedHashMap<>();
        for (String excludePath : forestProperty.getExcludePaths()) {
            map.put(excludePath, "anon");
        }
        for (String resourcePath : forestProperty.getResourcePaths()) {
            map.put(resourcePath, "authc");
        }
        map.put("/user/logout", "logout");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        return matcher;
    }

    @Bean
    @ConditionalOnProperty(prefix = "forest", name = "access-control", havingValue = "true")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        System.out.println("=======================开启Shiro访问权限控制!=======================");
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }
}

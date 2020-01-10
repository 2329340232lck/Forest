package com.demo.forest.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean(value = "securityManager")
    public SecurityManager securityManager(CustomRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //设置登录url
        factoryBean.setLoginUrl("/login.html");
        //设置登录成功的url
        factoryBean.setSuccessUrl("/index.html");
        Map<String, String> pathMap = new LinkedHashMap<>();
        //配置过滤器链,前者为要过滤的url，后者为使用哪个过滤器..根据过滤器名称匹配
        //路径添加的顺序也需注意。
        //放行登录接口
        pathMap.put("/user/login.ajax", "anon");
        //放行静态资源文件
        pathMap.put("/css/**", "anon");
        pathMap.put("/img/**", "anon");
        pathMap.put("/js/**", "anon");
        pathMap.put("/libs/**", "anon");
        //退出系统的过滤器
        pathMap.put("/user/logout.ajax", "logout");
        //除此之外,过滤所有请求
        pathMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(pathMap);
        return factoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        return matcher;
    }

    /**
     * 开启注解，需要SpringAop支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

}

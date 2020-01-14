package com.demo.forest.config.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//开启事务管理器
@EnableTransactionManagement
//mapper映射扫描
@MapperScan(basePackages = {"com.demo.forest.**.dao", "com.demo.forest.config.mybatis.mapper"})
public class MybatisConfig {

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //sql执行效率插件
    @Bean
    @Profile({"dev", "test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //设置SQL最大执行时长
        performanceInterceptor.setMaxTime(1500);
        return performanceInterceptor;
    }

}

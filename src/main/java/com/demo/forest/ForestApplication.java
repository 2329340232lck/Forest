package com.demo.forest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//开启Servlet组件扫描,例如过滤器,监听器之类,不配置basePackages默认扫描当前类所在包
@ServletComponentScan
//SpringBoot启动类注解
@SpringBootApplication
public class ForestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForestApplication.class, args);
        System.out.println("==================系统启动完成=================");
    }

}

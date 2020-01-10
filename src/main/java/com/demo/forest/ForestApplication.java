package com.demo.forest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForestApplication.class, args);
        System.out.println("==================系统启动完成=================");
    }

}

package com.zhxu.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaRegistry {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRegistry.class, args);
    }
}
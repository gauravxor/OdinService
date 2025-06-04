package com.clumsycoder.odinservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.clumsycoder.odinservice.clients")
public class OdinServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OdinServiceApplication.class, args);
    }

}
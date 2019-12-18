package com.mettre.moduleregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ModuleRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleRegisterApplication.class, args);
    }

}

package com.mettre.moduleclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaServer
@SpringBootApplication
@RestController
public class ModuleClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleClientApplication.class, args);
    }

    /**
     * 访问首页
     */
    @GetMapping("/index")
    public String index() {
        return "hello SpringBoot！";
    }

}

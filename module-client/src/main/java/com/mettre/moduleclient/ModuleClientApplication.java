package com.mettre.moduleclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
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

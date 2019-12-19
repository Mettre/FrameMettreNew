package com.mettre.modulegateway2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ModuleGateway2Application {

    public static void main(String[] args) {
        SpringApplication.run(ModuleGateway2Application.class, args);
    }

}

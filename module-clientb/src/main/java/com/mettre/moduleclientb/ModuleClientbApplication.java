package com.mettre.moduleclientb;

import com.mettre.modulecommon.jwt.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
@RestController
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@MapperScan("com.mettre.moduleclientb.mapper")
@EnableFeignClients(basePackages = {"com.mettre.moduleclientb.feign"})
public class ModuleClientbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleClientbApplication.class, args);
    }

    //过滤器
    @Bean
    public FilterRegistrationBean jwtFilter() {
        //拦截器
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/loginEd/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}

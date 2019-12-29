package com.mettre.moduleclient;

import com.mettre.modulecommon.jwt.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
@RestController
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@MapperScan("com.mettre.moduleclient.mapper")
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

    //过滤器
    @Bean
    public FilterRegistrationBean jwtFilter() {
        //拦截器
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/loginEd/*");
        urlPatterns.add("/api/user/loginEd/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

}

package com.mettre.modulegateway2;

import com.mettre.modulecommon.jwt.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
public class ModuleGateway2Application {

    public static void main(String[] args) {
        SpringApplication.run(ModuleGateway2Application.class, args);
    }


    //过滤器
    @Bean
    public FilterRegistrationBean jwtFilter() {
        //拦截器
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/loginEd/*");
        urlPatterns.add("/user/loginEd/*");
        urlPatterns.add("/account/loginEd/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}

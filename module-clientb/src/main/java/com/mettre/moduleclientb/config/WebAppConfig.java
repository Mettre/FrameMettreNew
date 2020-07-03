package com.mettre.moduleclientb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${spring.multipart.max-file-size}")
    private Long maxFileSize;

    @Value("${spring.multipart.max-request-size}")
    private Long maxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize(DataSize.of(maxFileSize, DataUnit.MEGABYTES));
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.of(maxRequestSize, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}
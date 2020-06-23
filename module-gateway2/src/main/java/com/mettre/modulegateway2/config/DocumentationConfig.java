package com.mettre.modulegateway2.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("（1）子服务A", "/module-client/v2/api-docs", "2.0"));
        resources.add(swaggerResource("（2）子服务B", "/module-client-b/v2/api-docs", "2.0"));
        resources.add(swaggerResource("（3）子服务C", "/account/v2/api-docs", "2.0"));
        resources.add(swaggerResource("（3）子服务D", "/module-friend/v2/api-docs", "2.0"));

        resources.add(swaggerResource("（2）子服务信息咨询系统", "/information/v2/api-docs", "2.0"));
        resources.add(swaggerResource("（3）子服务通用系统", "/usually/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
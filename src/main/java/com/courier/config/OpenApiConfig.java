package com.courier.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI courierManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Courier Management System API")
                        .description("REST API for managing courier services, including customers, packets, and payments")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Courier Management Team")
                                .email("support@couriermanagement.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
} 
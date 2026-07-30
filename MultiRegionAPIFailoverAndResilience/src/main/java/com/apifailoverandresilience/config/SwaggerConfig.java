package com.apifailoverandresilience.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Multi-Region API Failover & Resilience")
                        .description("REST APIs for Multi-Region API Failover & Resilience System")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Mahesh Kapilavai")
                                .email("mahesh@example.com")));
    }
}

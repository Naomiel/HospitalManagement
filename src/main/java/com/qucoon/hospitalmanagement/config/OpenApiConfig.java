package com.qucoon.hospitalmanagement.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("HospitalManagement Documentation")
                        .description("HospitalManagement Documentation")
                        .version("1.0.0"));
    }
}

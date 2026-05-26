package com.example.Eventify.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI eventifyOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Eventify API")
                        .description("API REST para la gestión de eventos y sedes")
                        .version("1.0.0"));
    }
}

package com.example.counter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Counter",
                description = "Counter", version = "1",
                contact = @Contact(
                        name = "Buanova Tatyana"
                )
        )
)
public class OpenApiConfig {
}

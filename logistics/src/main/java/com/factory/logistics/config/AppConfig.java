package com.factory.logistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class AppConfig {
    @Value("${openmeteo.api.url}")
    private String OpenMeteoApiUrl;
}

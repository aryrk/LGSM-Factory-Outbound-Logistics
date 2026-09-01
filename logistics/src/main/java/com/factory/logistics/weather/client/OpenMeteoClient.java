package com.factory.logistics.weather.client;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.factory.logistics.config.AppConfig;
import com.factory.logistics.weather.dto.OpenMeteo.OpenMeteoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenMeteoClient {
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;
    
    public OpenMeteoResponse fetch(BigDecimal latitude, BigDecimal longitude) {
        try{
            String url = UriComponentsBuilder.fromUriString(appConfig.getOpenMeteoApiUrl())
                    .queryParam("latitude", latitude)
                    .queryParam("longitude", longitude)
                    .queryParam("daily", "precipitation_sum,weather_code")
                    .queryParam("timezone", "auto")
                    .toUriString();

            return restTemplate.getForObject(url, OpenMeteoResponse.class);
        } catch (Exception e) {
            log.error("Error fetching weather data from OpenMeteo API", e);
            return null;
        }
    }
}

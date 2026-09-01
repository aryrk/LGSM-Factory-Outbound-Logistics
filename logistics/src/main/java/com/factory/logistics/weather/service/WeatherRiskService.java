package com.factory.logistics.weather.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.factory.logistics.shipment.enums.RiskLevel;
import com.factory.logistics.weather.client.OpenMeteoClient;
import com.factory.logistics.weather.dto.OpenMeteo.OpenMeteoResponse;
import com.factory.logistics.weather.entity.WeatherCache;
import com.factory.logistics.weather.repository.WeatherCacheRepository;
import com.factory.logistics.weather.util.WeatherRiskCalculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherRiskService {

    private final WeatherCacheRepository weatherCacheRepository;
    private final OpenMeteoClient openMeteoClient;
    private final WeatherRiskCalculator weatherRiskCalculator;

    public RiskLevel getRiskLevel(BigDecimal langitude, BigDecimal longitude, LocalDate checkForDate) {
        BigDecimal precipMm = getCachedPricipMm(langitude, longitude, checkForDate);
        if (precipMm != null) {
            return weatherRiskCalculator.calculateRiskLevel(precipMm);
        }

        Optional<WeatherFetchResult> fetchResult = fetchAndCacheWeatherData(langitude, longitude, checkForDate);
        if (fetchResult.isEmpty()){
            return RiskLevel.UNKNOWN;
        }

        saveToCache(langitude, longitude, checkForDate, fetchResult.get().precipMm(), fetchResult.get().weatherCode());
        return weatherRiskCalculator.calculateRiskLevel(fetchResult.get().precipMm());
    }

    public BigDecimal getCachedPricipMm(BigDecimal langitude, BigDecimal longitude, LocalDate checkForDate) {
        Optional<WeatherCache> cachedWeather = weatherCacheRepository.findByLatitudeAndLongitudeAndDate(langitude,
                longitude, checkForDate);

        if (cachedWeather.isPresent()) {
            return cachedWeather.get().getPrecipMm();
        }

        return null;
    }

    private record WeatherFetchResult(BigDecimal precipMm, Integer weatherCode) {
    }

    private Optional<WeatherFetchResult> fetchAndCacheWeatherData(BigDecimal latitude, BigDecimal longitude, LocalDate date) {
        OpenMeteoResponse response = openMeteoClient.fetch(latitude, longitude);
        if(response == null || response.getDaily()== null){
            return Optional.empty();
        }
        
        BigDecimal precipMm = weatherRiskCalculator.getPrecipitationAtDate(response, date);
        Integer weatherCode = weatherRiskCalculator.getWeatherCodeAtDate(response, date);

        if (precipMm == null || weatherCode == null) {
            return Optional.empty();
        }

        return Optional.of(new WeatherFetchResult(precipMm, weatherCode));
    }

    private void saveToCache(BigDecimal latitude, BigDecimal longitude, LocalDate date, BigDecimal precipMm, Integer weatherCode) {
        WeatherCache weatherCache = WeatherCache.builder()
                .destinationLatitude(latitude)
                .destinationLongitude(longitude)
                .forecastDate(date)
                .precipMm(precipMm)
                .weatherCode(weatherCode)
                .build();

        weatherCacheRepository.save(weatherCache);
    }

}

package com.factory.logistics.weather.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weather_cache")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherCache {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "dest_lat", nullable = false, precision = 9, scale = 6)
    private BigDecimal destinationLatitude;

    @Column(name = "dest_lng", nullable = false, precision = 9, scale = 6)
    private BigDecimal destinationLongitude;

    @Column(name = "forecast_date", nullable = false)
    private LocalDate forecastDate;

    @Column(name = "precip_mm", precision = 6, scale = 2)
    private BigDecimal precipMm;

    @Column(name = "weather_code")
    private Integer weatherCode;

    @CreationTimestamp
    @Column(name = "fetched_at")
    private LocalDateTime fetchedAt;
}

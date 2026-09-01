package com.factory.logistics.weather.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.factory.logistics.weather.entity.WeatherCache;

@Repository
public interface WeatherCacheRepository extends JpaRepository<WeatherCache, UUID> {

}

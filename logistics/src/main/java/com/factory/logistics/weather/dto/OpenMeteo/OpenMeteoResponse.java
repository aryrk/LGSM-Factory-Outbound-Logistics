package com.factory.logistics.weather.dto.OpenMeteo;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OpenMeteoResponse {

    private Daily daily;

    @Data
    public static class Daily{
        private List<String> time;

        @JsonProperty("precipitation_sum")
        private List<BigDecimal> precipSum;

        @JsonProperty("weather_code")
        private List<Integer> weatherCode;
    }
}

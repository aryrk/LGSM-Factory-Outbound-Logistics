package com.factory.logistics.weather.util;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.factory.logistics.shipment.enums.RiskLevel;
import com.factory.logistics.weather.dto.OpenMeteo.OpenMeteoResponse;

@Component
public class WeatherRiskCalculator {

    public RiskLevel calculateRiskLevel(BigDecimal precipMm) {
        if (precipMm == null) {
            return RiskLevel.UNKNOWN;
        }
        double precip = precipMm.doubleValue();
        // NOTE TO SELF: The requirementss says that "Risk Rule (use exactly this):
        // precipitation_sum for the dispatch date → 0mm = LOW, 1–10mm = MEDIUM,
        // &gt;10mm = HIGH."
        // It's currently using the exact rule, but please confirm if possible since
        // there will be gap, e.g: 0.5
        RiskLevel result = RiskLevel.UNKNOWN;
        if (precip == 0.0) {
            result = RiskLevel.LOW;
        } else if (precip >= 1.0 && precip <= 10.0) {
            result = RiskLevel.MEDIUM;
        } else if (precip > 10.0) {
            result = RiskLevel.HIGH;
        }
        return result;
    }

    public BigDecimal getPrecipitationAtDate(OpenMeteoResponse data, LocalDate date) {
        var daily = data.getDaily();
        if (daily.getTime().size() == 0)
            return null;

        for (int i = 0; i < daily.getTime().size(); i++) {
            if (daily.getTime().get(i).equals(date.toString())) {
                return daily.getPrecipSum().get(i);
            }
        }
        return null;
    }

    public Integer getWeatherCodeAtDate(OpenMeteoResponse data, LocalDate date) {
        var daily = data.getDaily();
        if (daily.getTime().size() == 0)
            return null;

        for (int i = 0; i < daily.getTime().size(); i++) {
            if (daily.getTime().get(i).equals(date.toString())) {
                return daily.getWeatherCode().get(i);
            }
        }
        return null;
    }
}

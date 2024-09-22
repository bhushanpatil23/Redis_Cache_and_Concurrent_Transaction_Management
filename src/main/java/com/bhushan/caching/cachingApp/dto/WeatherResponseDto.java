package com.bhushan.caching.cachingApp.dto;

import com.bhushan.caching.cachingApp.weather.MainWeather;
import com.bhushan.caching.cachingApp.weather.Wind;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponseDto {

    private String currentWeather;
    private MainWeather mainWeather;
    private int visibility;
    private Wind wind;
    private Long sunrise;
    private Long sunset;
}


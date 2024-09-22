package com.bhushan.caching.cachingApp.services;

import com.bhushan.caching.cachingApp.dto.WeatherResponseDto;

public interface WeatherService {
    public WeatherResponseDto getWeather(String city);
}

package com.bhushan.caching.cachingApp.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainWeather{

    private double temp;        // Temperature in Kelvin
    private double feels_like;  // Feels like temperature
    private double temp_min;    // Minimum temperature
    private double temp_max;    // Maximum temperature
    private double pressure;       // Atmospheric pressure
    private double humidity;       // Humidity percentage


}

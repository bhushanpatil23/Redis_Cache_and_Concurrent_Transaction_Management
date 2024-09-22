package com.bhushan.caching.cachingApp.controllers;

import com.bhushan.caching.cachingApp.dto.WeatherResponseDto;
import com.bhushan.caching.cachingApp.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    ResponseEntity<WeatherResponseDto> getWeatherByCity(@PathVariable String city){
        return ResponseEntity.ok(weatherService.getWeather(city));
    }

}

package com.bhushan.caching.cachingApp.services.impl;

import com.bhushan.caching.cachingApp.dto.WeatherResponseDto;
import com.bhushan.caching.cachingApp.services.WeatherService;
import com.bhushan.caching.cachingApp.weather.MainWeather;
import com.bhushan.caching.cachingApp.weather.Wind;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private static final String API_KEY = "1234567SRTYVHBNM834YFIEBWIUYWEFSNH";
    private static final String WEATHER_API_BASE_URL = "https://api.openweathermap.org";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";
    private final String WEATHER_CACHE_NAME = "weather";

    @Override
    @Cacheable(cacheNames = WEATHER_CACHE_NAME, key = "#city")
    public WeatherResponseDto getWeather(String city) {

        // Create URI with placeholders for city and API key
        URI uri = URI.create(WEATHER_API_URL.replace("{city}",city).replace("{apiKey}",API_KEY));

        // Call the API using RestClient and get the full response as WeatherResponse

        RestClient restClient = RestClient.builder()
                .baseUrl(WEATHER_API_BASE_URL)
                .build();

        WeatherResponse fullResponse = restClient
                .get()
                .uri(uri)
                .retrieve()
                .body(WeatherResponse.class);

        MainWeather mainWeather = MainWeather.builder()
                .feels_like(fullResponse.getMain().getFeelsLike())
                .temp(fullResponse.getMain().getTemp())
                .temp_min(fullResponse.getMain().getTempMin())
                .temp_max(fullResponse.getMain().getTempMax())
                .humidity(fullResponse.getMain().getHumidity())
                .pressure(fullResponse.getMain().getPressure())
                .build();

        Wind wind = Wind.builder()
                .deg(fullResponse.getWind().getDeg())
                .speed(fullResponse.getWind().getSpeed())
                .build();

        WeatherResponseDto weatherResponseDto = WeatherResponseDto.builder()
                .currentWeather(String.valueOf(fullResponse.getWeather().getLast().getDescription()))
                .mainWeather(mainWeather)
                .wind(wind)
                .sunset(fullResponse.getSys().getSunset())
                .sunrise(fullResponse.getSys().getSunrise())
                .visibility(fullResponse.getVisibility())
                .build();

        return weatherResponseDto;
    }
}

@Data
class WeatherResponse{

    private List<Weather> weather; // List for weather description
    private Main main;              // Main weather data
    private int visibility;          // Visibility in meters
    private Wind wind;              // Wind data
    private Sys sys;                // System data with sunrise and sunset
    private int timezone;            // Timezone offset in seconds

    @Data
    public static class Weather {
        private String main; // Main weather condition
        private String description; // Weather description
    }

    @Data
    public static class Main {
        private double temp;
        private double feelsLike;
        private double tempMin;
        private double tempMax;
        private double pressure;
        private double humidity;
    }

    @Data
    public static class Wind {
        private double speed; // Wind speed
        private int deg;      // Wind direction
    }

    @Data
    public static class Sys {
        private long sunrise; // Sunrise time in UNIX timestamp
        private long sunset;  // Sunset time in UNIX timestamp
    }
}

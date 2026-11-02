package com.vadlap.weather.client;

import com.vadlap.weather.common.WeatherResponseException;
import com.vadlap.weather.entity.dto.LocationResponse;
import com.vadlap.weather.entity.dto.WeatherResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class OpenWeatherClient {
    private final String apiKey = "99dc214a6f3dd80351e4e4848b74f5f1";
    private final HttpClient client;
    private final JsonMapper mapper;

    public OpenWeatherClient() {
        client = HttpClient
                    .newBuilder()
                    .connectTimeout(Duration.ofSeconds(3))
                    .build();
        mapper = JsonMapper.builder().build();
    }

    public LocationResponse searchLocation(String cityName) throws IOException, InterruptedException {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openweathermap.org/geo/1.0/direct")
                .queryParam("q", cityName)
                .queryParam("limit", 5)
                .queryParam("appid", apiKey)
                .build()
                .encode()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), LocationResponse.class);
        } else {
            throw new WeatherResponseException("GET weather data exception " + response.statusCode());
        }
    }

    public WeatherResponse getWeather(double latitude, double longitude) throws IOException, InterruptedException {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("units", "metric")
                .queryParam("lang", "ru")
                .queryParam("appid", apiKey)
                .build()
                .encode()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), WeatherResponse.class);
        } else {
            throw new WeatherResponseException("GET weather data exception " + response.statusCode());
        }
    }
}

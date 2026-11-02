package com.vadlap.weather;

import com.vadlap.weather.entity.dto.WeatherResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class WeatherApplication {
	public static void main(String[] args) { SpringApplication.run(WeatherApplication.class, args); }
}

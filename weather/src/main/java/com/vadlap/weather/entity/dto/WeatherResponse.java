package com.vadlap.weather.entity.dto;

public record WeatherResponse(
    String name,
    WeatherMain main
) {}

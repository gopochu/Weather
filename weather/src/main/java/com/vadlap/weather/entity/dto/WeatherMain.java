package com.vadlap.weather.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherMain(
    double temp,

    @JsonProperty("feels_like")
    double feelsLike,

    @JsonProperty("temp_min")
    double tempMin,

    @JsonProperty("temp_max")
    double tempMax,

    int pressure,
    int humidity,

    @JsonProperty("sea_level")
    int seaLevel,

    @JsonProperty("grnd_level")
    int grndLevel
) {}

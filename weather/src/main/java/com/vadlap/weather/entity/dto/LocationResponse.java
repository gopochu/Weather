package com.vadlap.weather.entity.dto;

public record LocationResponse(
   String city,
   String region,
   String country
) {}

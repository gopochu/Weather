package com.vadlap.weather.common;

public record ApiError(
   String code,
   String error
) {}

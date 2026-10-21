package com.vadlap.weather.entity;

@org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    com.vadlap.weather.entity.User toEntity(UserResponse userResponse);

    UserResponse toUserResponse(com.vadlap.weather.entity.User user);
}
package com.mlast.userapi.mapper;

import com.mlast.userapi.dto.request.CreateUserRequest;
import com.mlast.userapi.dto.response.UserResponse;
import com.mlast.userapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    UserResponse toResponse(User user);

    @Mapping(target = "id",           ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "role",         ignore = true)
    @Mapping(target = "enabled",      ignore = true)
    @Mapping(target = "createdAt",    ignore = true)
    @Mapping(target = "updatedAt",    ignore = true)
    User toEntity(CreateUserRequest request);
}

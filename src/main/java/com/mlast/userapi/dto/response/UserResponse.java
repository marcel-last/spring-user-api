package com.mlast.userapi.dto.response;

import java.time.Instant;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String role,
        boolean enabled,
        Instant createdAt,
        Instant updatedAt
) {}

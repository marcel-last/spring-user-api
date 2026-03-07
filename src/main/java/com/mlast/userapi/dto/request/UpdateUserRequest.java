package com.mlast.userapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @Size(max = 100)
        String firstName,

        @Size(max = 100)
        String lastName,

        @Email(message = "Must be a valid email address")
        @Size(max = 255)
        String email
) {}

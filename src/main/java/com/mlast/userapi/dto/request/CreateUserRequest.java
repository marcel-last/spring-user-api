package com.mlast.userapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

        @NotBlank(message = "First name is required.")
        @Size(max = 100)
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 100)
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Must be a valid email address.")
        @Size(max = 255)
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 12, max = 128, message = "Password must be at least 12 characters long.")
        String password
) {}

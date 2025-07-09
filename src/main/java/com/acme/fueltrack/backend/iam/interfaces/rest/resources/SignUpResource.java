package com.acme.fueltrack.backend.iam.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema
public record SignUpResource(
        @Schema(example = "johndoe")
        String username,

        @Schema(example = "john.doe@example.com")
        String email,

        @Schema(example = "password123")
        String password,

        @Schema(example = "[\"ROLE_ADMIN\"]")
        List<String> roles
) {}

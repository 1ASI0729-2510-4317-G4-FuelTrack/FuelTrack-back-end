package com.acme.fueltrack.backend.iam.interfaces.rest.resources;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record SignInResource(
        @Schema(example = "john.doe@example.com")
        String email,
        @Schema(example = "password123")
        String password)
{}

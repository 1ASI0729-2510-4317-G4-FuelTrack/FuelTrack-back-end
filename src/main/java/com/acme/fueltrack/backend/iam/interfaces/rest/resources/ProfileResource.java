package com.acme.fueltrack.backend.iam.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProfileResource(
        @Schema(example = "1")
        Long id,

        @Schema(example = "ROLE_ADMIN")
        String rol,

        @Schema(example = "WRITE_USERS")
        String permissions

)
{}

package com.acme.fueltrack.backend.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(
        Long id,
        String username,
        String email,
        long profileId
)
{}

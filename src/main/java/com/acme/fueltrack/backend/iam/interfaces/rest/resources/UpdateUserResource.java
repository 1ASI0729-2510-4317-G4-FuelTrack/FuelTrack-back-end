package com.acme.fueltrack.backend.iam.interfaces.rest.resources;

import java.util.Set;


public record UpdateUserResource(
        String username,
        String email,
        Set<String> roles
) {}
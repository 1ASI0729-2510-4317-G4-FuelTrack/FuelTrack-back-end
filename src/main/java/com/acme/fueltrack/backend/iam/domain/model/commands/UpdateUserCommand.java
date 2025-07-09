package com.acme.fueltrack.backend.iam.domain.model.commands;

import java.util.Set;

public record UpdateUserCommand(
        Long userId,
        String username,
        String email,
        Set<String> roles
) {}
package com.acme.fueltrack.backend.iam.domain.model.commands;

import com.acme.fueltrack.backend.iam.domain.model.aggregates.Profile;

import java.util.List;

public record SignUpCommand(String username, String email, String password, List<Profile> roles) {
}
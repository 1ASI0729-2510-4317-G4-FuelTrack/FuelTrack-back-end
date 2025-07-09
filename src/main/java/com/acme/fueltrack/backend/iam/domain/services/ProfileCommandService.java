package com.acme.fueltrack.backend.iam.domain.services;

import com.acme.fueltrack.backend.iam.domain.model.commands.SeedRolesCommand;

public interface ProfileCommandService {

    void handle(SeedRolesCommand command);
}

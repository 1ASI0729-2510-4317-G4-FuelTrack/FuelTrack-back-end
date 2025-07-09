package com.acme.fueltrack.backend.iam.interfaces.rest.transform;

import com.acme.fueltrack.backend.iam.domain.model.commands.UpdateUserCommand;
import com.acme.fueltrack.backend.iam.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(
                userId,
                resource.username(),
                resource.email(),
                resource.roles()
        );
    }
}
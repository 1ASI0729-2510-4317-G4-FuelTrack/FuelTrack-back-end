package com.acme.fueltrack.backend.iam.interfaces.rest.transform;

import com.acme.fueltrack.backend.iam.domain.model.aggregates.Profile;
import com.acme.fueltrack.backend.iam.domain.model.commands.SignUpCommand;
import com.acme.fueltrack.backend.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Profile.toProfileFromName(name)).toList() : new ArrayList<Profile>();
        return new SignUpCommand(resource.username(), resource.email(), resource.password(), roles);
    }
}

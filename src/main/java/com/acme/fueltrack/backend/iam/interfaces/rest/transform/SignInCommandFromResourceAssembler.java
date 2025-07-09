package com.acme.fueltrack.backend.iam.interfaces.rest.transform;

import com.acme.fueltrack.backend.iam.domain.model.commands.SignInCommand;
import com.acme.fueltrack.backend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}

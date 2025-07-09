package com.acme.fueltrack.backend.iam.interfaces.rest.transform;

import com.acme.fueltrack.backend.iam.domain.model.aggregates.Profile;
import com.acme.fueltrack.backend.iam.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile role) {
        return new ProfileResource(role.getId(), role.getStringName());
    }
}

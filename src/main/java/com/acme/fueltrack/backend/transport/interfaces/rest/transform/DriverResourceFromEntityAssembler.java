package com.acme.fueltrack.backend.transport.interfaces.rest.transform;

import com.acme.fueltrack.backend.transport.domain.model.aggregates.Driver;
import com.acme.fueltrack.backend.transport.interfaces.rest.resource.CreateDriverResource;
import com.acme.fueltrack.backend.transport.interfaces.rest.resource.DriverResource;

public class DriverResourceFromEntityAssembler {
    public static DriverResource toResourceFromEntity(Driver entity) {
        return new DriverResource(
                entity.getId(),
                entity.getFullName(),
                entity.getDni(),
                entity.getEmailAddress(),
                entity.getVehicle()
        );
    }
}

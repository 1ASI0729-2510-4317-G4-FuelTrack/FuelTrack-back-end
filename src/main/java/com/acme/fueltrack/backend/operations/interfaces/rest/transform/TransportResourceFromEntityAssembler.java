package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Transport;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.TransportResource;

public class TransportResourceFromEntityAssembler {

    public static TransportResource toResource(Transport entity) {
        TransportResource resource = new TransportResource();
        resource.setId(entity.getId());
        resource.setPlate(entity.getPlate());
        resource.setDriver(entity.getDriver());
        resource.setTank(entity.getTank());
        resource.setAvailable(entity.isAvailable()); // Asegúrate de que el getter 'isAvailable()' existe
        return resource;
    }
}

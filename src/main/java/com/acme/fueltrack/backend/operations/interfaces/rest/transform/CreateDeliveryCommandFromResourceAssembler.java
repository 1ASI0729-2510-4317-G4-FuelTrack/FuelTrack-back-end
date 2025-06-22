package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.commands.CreateDeliveryCommand;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.CreateDeliveryResource;

public class CreateDeliveryCommandFromResourceAssembler {

    public static CreateDeliveryCommand toCommand(CreateDeliveryResource resource) {
        return new CreateDeliveryCommand(
                resource.getDeliveryAt(),
                resource.getReceivedBy(),
                resource.getLocation(),
                resource.getOrderId(),
                resource.getTransportId()
        );
    }
}

package com.acme.fueltrack.backend.operations.interfaces.rest.transform;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;
import com.acme.fueltrack.backend.operations.interfaces.rest.resources.DeliveryResource;

public class DeliveryResourceFromEntityAssembler {

    public static DeliveryResource toResource(Delivery entity) {
        DeliveryResource resource = new DeliveryResource();
        resource.setId(entity.getId());
        resource.setDeliveryAt(entity.getDeliveryAt());
        resource.setReceivedBy(entity.getReceivedBy());
        resource.setLocation(entity.getLocation());
        resource.setOrderId(entity.getOrderId());
        resource.setTransportId(entity.getTransportId());
        return resource;
    }
}

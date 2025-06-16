package com.acme.fueltrack.backend.operations.domain.services;

import com.acme.fueltrack.backend.operations.domain.model.commands.CreateDeliveryCommand;
import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;

public interface DeliveryCommandService {
    Delivery handle(CreateDeliveryCommand command);
}

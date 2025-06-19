package com.acme.fueltrack.backend.operations.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateDeliveryCommand(
        Long id,
        LocalDateTime deliveryAt,
        String receivedBy,
        String location,
        Long orderId,
        Long transportId
) {}

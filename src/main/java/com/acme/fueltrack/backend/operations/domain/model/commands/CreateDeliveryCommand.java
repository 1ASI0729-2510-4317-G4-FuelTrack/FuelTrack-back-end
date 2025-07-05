package com.acme.fueltrack.backend.operations.domain.model.commands;

import java.time.LocalDateTime;

public record CreateDeliveryCommand(
        LocalDateTime deliveryAt,
        String receivedBy,
        String location,
        Long orderId,
        Long transportId
) {}

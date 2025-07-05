package com.acme.fueltrack.backend.operations.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateDeliveryResource(
    LocalDateTime deliveryAt,
    String receivedBy,
    String location,
    Long orderId,
    Long transportId
) {}

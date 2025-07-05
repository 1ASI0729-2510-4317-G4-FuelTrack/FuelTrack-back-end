package com.acme.fueltrack.backend.operations.domain.model.queries;

// Luciana: Returns all deliveries linked to a specific order
public record GetDeliveriesByOrderIdQuery(Long orderId) {
}

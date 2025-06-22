package com.acme.fueltrack.backend.operations.domain.model.queries;

// Luciana: Returns all deliveries assigned to a specific transport
public record GetDeliveriesByTransportIdQuery(Long transportId) {
}

package com.acme.fueltrack.backend.operations.interfaces.rest.resources;

public record UpdateTransportResource(
    String plate,
    String driver,
    String tank,
    boolean available
) {}

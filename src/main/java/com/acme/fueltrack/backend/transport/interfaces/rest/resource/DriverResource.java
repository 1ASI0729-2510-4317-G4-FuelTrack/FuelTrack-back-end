package com.acme.fueltrack.backend.transport.interfaces.rest.resource;

public record DriverResource (
        Long id,
        String fullName,
        String dni,
        String email,
        String vehicle

) {
}

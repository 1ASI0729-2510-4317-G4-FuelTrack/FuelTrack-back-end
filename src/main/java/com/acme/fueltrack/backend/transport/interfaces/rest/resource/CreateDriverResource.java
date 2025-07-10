package com.acme.fueltrack.backend.transport.interfaces.rest.resource;

public record CreateDriverResource(
        String firstName, String lastName, String dni, String email, String plate, String brand, String model, int year, int tankCapacity
) {
}

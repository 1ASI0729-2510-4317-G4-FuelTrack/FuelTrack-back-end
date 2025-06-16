package com.acme.fueltrack.backend.orders.interfaces.rest.resources;

public record CreateFuelOrderResource(String requesterId, String fuelType, double quantity) {}

package com.acme.fueltrack.backend.orders.interfaces.rest.resources;

public record CompletePaymentResource(double amount, String method) {}

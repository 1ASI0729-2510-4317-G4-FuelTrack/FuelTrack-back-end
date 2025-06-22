package com.acme.fueltrack.backend.operations.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InventoryResource(
        Long id,
        String fuelType,
        BigDecimal availableQt,
        LocalDateTime lastUpdate,
        Long supplierId
) {}

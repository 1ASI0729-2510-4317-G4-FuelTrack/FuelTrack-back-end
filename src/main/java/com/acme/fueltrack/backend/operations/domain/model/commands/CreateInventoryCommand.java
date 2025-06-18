package com.acme.fueltrack.backend.operations.domain.model.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateInventoryCommand(
    String fuelType,
    BigDecimal availableQt,
    LocalDateTime lastUpdate,
    Long supplierId
) {}

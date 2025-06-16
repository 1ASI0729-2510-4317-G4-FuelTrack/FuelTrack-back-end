package com.acme.fueltrack.backend.orders.domain.model.aggregates;

import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.OrderStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FuelOrder {
    private final UUID orderId;
    private final UUID requesterId;
    private final FuelType fuelType;
    private final double quantity;
    private OrderStatus status;

    public FuelOrder(UUID requesterId, FuelType fuelType, double quantity) {
        this.orderId = UUID.randomUUID();
        this.requesterId = requesterId;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.status = OrderStatus.PENDING;
    }

    public void markAsProcessed() {
        this.status = OrderStatus.PROCESSED;
    }
}

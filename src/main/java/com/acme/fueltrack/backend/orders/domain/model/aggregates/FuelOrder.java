package com.acme.fueltrack.backend.orders.domain.model.aggregates;

import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.OrderStatus;
import lombok.Getter;

import java.util.UUID;
import jakarta.persistence.*;

@Getter
@Entity
@Table(name = "fuel_orders")
public class FuelOrder {

    // Getters
    @Id
    @GeneratedValue
    private UUID orderId;

    private UUID requesterId;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private double quantity;
    private String note;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // Constructor requerido por JPA
    protected FuelOrder() {}

    public FuelOrder(UUID requesterId, FuelType fuelType, double quantity, String note) {
        this.requesterId = requesterId;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.status = OrderStatus.PENDING;
        this.note = note;
    }

    public void markAsProcessed() {
        this.status = OrderStatus.PROCESSED;
    }
}

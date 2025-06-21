package com.acme.fueltrack.backend.orders.domain.model.aggregates;

import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.OrderStatus;
import lombok.Getter;

import java.util.UUID;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Entity
@Table(name = "fuel_orders")
public class FuelOrder {

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

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Constructor requerido por JPA
    protected FuelOrder() {}

    public FuelOrder(UUID requesterId, FuelType fuelType, double quantity, String note) {
        this.requesterId = requesterId;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.note = note;
        this.status = OrderStatus.PENDING;
    }

    public void markAsProcessed() {
        this.status = OrderStatus.PROCESSED;
    }
}

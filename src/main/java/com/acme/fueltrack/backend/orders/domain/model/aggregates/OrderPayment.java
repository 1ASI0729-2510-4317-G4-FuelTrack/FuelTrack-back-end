package com.acme.fueltrack.backend.orders.domain.model.aggregates;

import com.acme.fueltrack.backend.orders.domain.model.valueobjects.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "order_payments")
public class OrderPayment {

    @Id
    @GeneratedValue
    private UUID paymentId;

    @OneToOne
    @JoinColumn(name = "fuel_order_id", nullable = false, unique = true)
    private FuelOrder fuelOrder;

    private double amount;
    private String method; // ej: tarjeta, transferencia, etc.

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    protected OrderPayment() {}

    public OrderPayment(FuelOrder fuelOrder) {
        this.fuelOrder = fuelOrder;
        this.amount = 0.0; // Valor inicial por defecto
        this.status = PaymentStatus.PENDING;
    }

    public void completePayment(double amount, String method) {
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.COMPLETED;
    }
}

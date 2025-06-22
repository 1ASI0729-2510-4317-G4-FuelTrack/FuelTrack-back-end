package com.acme.fueltrack.backend.orders.infrastuctrure.persistence;

import com.acme.fueltrack.backend.orders.domain.model.aggregates.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, UUID> {
}

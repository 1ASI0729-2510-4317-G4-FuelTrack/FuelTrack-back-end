package com.acme.fueltrack.backend.orders.infrastuctrure.persistence;

import com.acme.fueltrack.backend.orders.domain.model.aggregates.FuelOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FuelOrderRepository extends JpaRepository<FuelOrder, UUID> {
    List<FuelOrder> findByRequesterId(UUID requesterId);
}

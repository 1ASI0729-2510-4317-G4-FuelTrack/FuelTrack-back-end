package com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findById(Long id);

    List<Inventory> findByFuelType(String fuelType);

    List<Inventory> findBySupplierId(Long supplierId);
}

package com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findById(Long id);

    List<Delivery> findByOrderId(Long orderId);

    List<Delivery> findByTransportId(Long transportId);
}

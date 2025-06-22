package com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.acme.fueltrack.backend.operations.domain.model.aggregates.Transport;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    Optional<Transport> findById(Long id);
    List<Transport> findByAvailableTrue(); // Para GetAvailableTransportsQuery
}
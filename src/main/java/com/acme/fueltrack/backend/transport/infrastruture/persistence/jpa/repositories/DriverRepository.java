package com.acme.fueltrack.backend.transport.infrastruture.persistence.jpa.repositories;

import com.acme.fueltrack.backend.transport.domain.model.aggregates.Driver;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.Dni;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {

    Optional<Driver> findByEmail(EmailAddress email);
    Optional<Driver> findByDni(Dni dni);

}

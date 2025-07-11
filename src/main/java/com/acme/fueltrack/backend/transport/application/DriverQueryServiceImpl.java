package com.acme.fueltrack.backend.transport.application;

import com.acme.fueltrack.backend.transport.domain.model.aggregates.Driver;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetAllDriverQuery;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByDniQuery;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByEmailQuery;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByIdQuery;
import com.acme.fueltrack.backend.transport.domain.services.DriverQueryService;
import com.acme.fueltrack.backend.transport.infrastruture.persistence.jpa.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverQueryServiceImpl implements DriverQueryService {

    private final DriverRepository driverRepository;

    public DriverQueryServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Optional<Driver> handle(GetDriverByEmailQuery query) {
        return this.driverRepository.findByEmail(query.email());
    }

    @Override
    public Optional<Driver> handle(GetDriverByDniQuery query) {
        return this.driverRepository.findByDni(query.dni());
    }

    @Override
    public Optional<Driver> handle(GetDriverByIdQuery query) {
        return this.driverRepository.findById(query.id());
    }

    @Override
    public List<Driver> handle() {
        return this.driverRepository.findAll();
    }
}
package com.acme.fueltrack.backend.transport.domain.services;

import com.acme.fueltrack.backend.transport.domain.model.aggregates.Driver;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetAllDriverQuery;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByDniQuery;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByEmailQuery;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DriverQueryService {
    Optional<Driver> handle(GetDriverByIdQuery query);
    Optional<Driver> handle(GetDriverByEmailQuery query);
    Optional<Driver> handle(GetDriverByDniQuery query);
    List<Driver> handle();
}

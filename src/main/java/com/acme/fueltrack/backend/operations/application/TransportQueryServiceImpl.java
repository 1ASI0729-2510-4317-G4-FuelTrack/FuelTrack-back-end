package com.acme.fueltrack.backend.operations.application;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Transport;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetAllTransportQuery;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetAvailableTransportQuery;
import com.acme.fueltrack.backend.operations.domain.model.queries.GetTransportByIdQuery;
import com.acme.fueltrack.backend.operations.domain.services.TransportQueryService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportQueryServiceImpl implements TransportQueryService {

    private final TransportRepository transportRepository;

    public TransportQueryServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public List<Transport> handle(GetAllTransportQuery query) {
        return transportRepository.findAll();
    }

    @Override
    public List<Transport> handle(GetAvailableTransportQuery query) {
        return transportRepository.findAll().stream()
                .filter(Transport::isAvailable)
                .toList();
    }

    @Override
    public Optional<Transport> handle(GetTransportByIdQuery query) {
        return transportRepository.findById(query.transportId());
    }
}

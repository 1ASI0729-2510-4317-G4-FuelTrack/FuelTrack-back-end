package com.acme.fueltrack.backend.operations.application.internal.queryservice;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;
import com.acme.fueltrack.backend.operations.domain.services.DeliveryQueryService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryQueryServiceImpl implements DeliveryQueryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryQueryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<Delivery> handleGetAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Optional<Delivery> handleGetDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public List<Delivery> handleGetDeliveriesByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId); // ✅ usa List
    }

    @Override
    public List<Delivery> handleGetDeliveriesByTransportId(Long transportId) {
        return deliveryRepository.findByTransportId(transportId);
    }
}

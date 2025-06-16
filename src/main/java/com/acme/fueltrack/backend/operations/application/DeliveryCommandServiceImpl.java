package com.acme.fueltrack.backend.operations.application;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateDeliveryCommand;
import com.acme.fueltrack.backend.operations.domain.services.DeliveryCommandService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryCommandServiceImpl implements DeliveryCommandService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryCommandServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery handle(CreateDeliveryCommand command) {
        Delivery delivery = new Delivery(
                command.deliveryAt(),
                command.receivedBy(),
                command.location(),
                command.orderId(),
                command.transportId()
        );
        return deliveryRepository.save(delivery);
    }
}

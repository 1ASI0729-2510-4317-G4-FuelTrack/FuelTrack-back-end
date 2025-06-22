package com.acme.fueltrack.backend.operations.application.internal.commandservice;

import com.acme.fueltrack.backend.operations.domain.model.aggregates.Delivery;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateDeliveryCommand;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateDeliveryCommand;
import com.acme.fueltrack.backend.operations.domain.services.DeliveryCommandService;
import com.acme.fueltrack.backend.operations.infrastructure.persistence.jpa.repositories.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryCommandServiceImpl implements DeliveryCommandService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryCommandServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Optional<Delivery> handle(CreateDeliveryCommand command) {
        Delivery delivery = new Delivery(
                command.deliveryAt(),
                command.receivedBy(),
                command.location(),
                command.orderId(),
                command.transportId()
        );
        return Optional.of(deliveryRepository.save(delivery));
    }

    @Override
    public Optional<Delivery> updateDelivery(UpdateDeliveryCommand command) {
        return deliveryRepository.findById(command.id())
                .map(delivery -> {
                    delivery.updateWithCommand(command);
                    return deliveryRepository.save(delivery);
                });
    }
}

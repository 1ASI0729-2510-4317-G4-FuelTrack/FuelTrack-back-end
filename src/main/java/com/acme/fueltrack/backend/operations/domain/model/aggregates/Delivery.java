package com.acme.fueltrack.backend.operations.domain.model.aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.acme.fueltrack.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateDeliveryCommand;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery extends AuditableAbstractAggregateRoot<Delivery> {

    private LocalDateTime deliveryAt;
    private String receivedBy;
    private String location;
    private Long orderId;
    private Long transportId;

    public Delivery(LocalDateTime deliveryAt, String receivedBy, String location, Long orderId, Long transportId) {
        this.deliveryAt = deliveryAt;
        this.receivedBy = receivedBy;
        this.location = location;
        this.orderId = orderId;
        this.transportId = transportId;
    }

    public Delivery updateWithCommand(UpdateDeliveryCommand command) {
        this.deliveryAt = command.deliveryAt();
        this.receivedBy = command.receivedBy();
        this.location = command.location();
        this.orderId = command.orderId();
        this.transportId = command.transportId();
        return this;
    }
}

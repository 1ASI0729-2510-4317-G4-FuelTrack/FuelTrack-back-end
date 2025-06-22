package com.acme.fueltrack.backend.operations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import com.acme.fueltrack.backend.operations.domain.model.commands.CreateTransportCommand;
import com.acme.fueltrack.backend.operations.domain.model.commands.UpdateTransportCommand;
import com.acme.fueltrack.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Transport extends AuditableAbstractAggregateRoot<Transport> {

    @NotBlank(message = "La placa no puede estar vacía")
    private String plate;

    @NotBlank(message = "El nombre del conductor no puede estar vacío")
    private String driver;

    @NotBlank(message = "El tipo de tanque no puede estar vacío")
    private String tank;

    private boolean available = true;

    public Transport() {
    }

    public Transport(CreateTransportCommand command) {
        this.plate = command.plate();
        this.driver = command.driver();
        this.tank = command.tank();
    }

    public Transport updateWithCommand(UpdateTransportCommand command) {
        this.plate = command.plate();
        this.driver = command.driver();
        this.tank = command.tank();
        this.available = command.available();
        return this;
    }


}
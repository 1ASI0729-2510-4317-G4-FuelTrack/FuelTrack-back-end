package com.acme.fueltrack.backend.validations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import com.acme.fueltrack.backend.validations.domain.model.commands.CreateNotificationCommand;
import com.acme.fueltrack.backend.validations.domain.model.commands.MarkNotificationAsReadCommand;
import com.acme.fueltrack.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;

@Getter
@Entity
public class Notification extends AuditableAbstractAggregateRoot<Notification> {

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;

    private boolean alreadyRead;

    private int usersId;

    private int ordersId;

    public Notification() {}

    public Notification(CreateNotificationCommand command) {
        this.message = command.message();
        this.alreadyRead = command.alreadyRead();
        this.usersId = command.usersId();
        this.ordersId = command.ordersId();
    }

    public Notification markAsRead(MarkNotificationAsReadCommand command) {
        this.alreadyRead = true;
        return this;
    }
}

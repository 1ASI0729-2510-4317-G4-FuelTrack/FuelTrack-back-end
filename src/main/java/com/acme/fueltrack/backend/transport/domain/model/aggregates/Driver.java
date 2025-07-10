package com.acme.fueltrack.backend.transport.domain.model.aggregates;

import com.acme.fueltrack.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.fueltrack.backend.transport.domain.model.commands.CreateDriverCommand;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.Dni;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.EmailAddress;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.PersonName;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Driver {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded Dni dni;

    @Embedded
    EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "plate", column = @Column(name = "vehicle_plate")),
            @AttributeOverride(name = "brand", column = @Column(name = "vehicle_brand")),
            @AttributeOverride(name = "model", column = @Column(name = "vehicle_model")),
            @AttributeOverride(name = "year", column = @Column(name = "vehicle_year")),
            @AttributeOverride(name = "tankCapacity", column = @Column(name = "vehicle_tankCapacity")),
    })
    private Vehicle vehicle;

    public Driver(){}

    public Driver(String firstName, String lastName, String dni, String email, String plate, String brand, String model, int year, int tankCapacity) {
        this.name = new PersonName(firstName, lastName);
        this.dni = new Dni(dni);
        this.email = new EmailAddress(email);
        this.vehicle = new Vehicle(plate, brand, model, year, tankCapacity);
    }

    public Driver(CreateDriverCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.dni = new Dni(command.dni());
        this.email = new EmailAddress(command.email());
        this.vehicle = new Vehicle(command.plate(), command.brand(), command.model(), command.year(), command.tankCapacity());
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getVehicle() {
        return vehicle.toSummary();
    }

    public String getDni(){
        return dni.dni();
    }

    public String getEmailAddress() {
        return email.email();
    }




}
